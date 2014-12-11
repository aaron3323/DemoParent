package com.demo.subsystem.common;

import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.session.HashMapBackedSessionMappingStorage;
import org.jasig.cas.client.session.SessionMappingStorage;
import org.jasig.cas.client.session.SingleSignOutHandler;
import org.jasig.cas.client.util.AbstractConfigurationFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.util.XmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;

public class CustomSingleSignOutFilter extends AbstractConfigurationFilter {
	
	@Autowired
	@Qualifier("sessionRegistry")
	private SessionRegistry sessionRegistry;

    private static final SingleSignOutHandler HANDLER = new SingleSignOutHandler();

    private AtomicBoolean handlerInitialized = new AtomicBoolean(false);
	
	private String artifactParameterName;  
	private static SessionMappingStorage SESSION_MAPPING_STORAGE = new HashMapBackedSessionMappingStorage();  
	private static Log log = LogFactory.getLog(CustomSingleSignOutFilter.class);
	
	public CustomSingleSignOutFilter() {
		this.artifactParameterName = "ticket";
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		setArtifactParameterName(getPropertyFromInitParams(filterConfig, "artifactParameterName", "ticket"));  
	    init();
	    
        if (!isIgnoreInitConfiguration()) {
            HANDLER.setArtifactParameterName(getPropertyFromInitParams(filterConfig, "artifactParameterName",
                    SingleSignOutHandler.DEFAULT_ARTIFACT_PARAMETER_NAME));
            HANDLER.setLogoutParameterName(getPropertyFromInitParams(filterConfig, "logoutParameterName",
                    SingleSignOutHandler.DEFAULT_LOGOUT_PARAMETER_NAME));
            HANDLER.setFrontLogoutParameterName(getPropertyFromInitParams(filterConfig, "frontLogoutParameterName",
                    SingleSignOutHandler.DEFAULT_FRONT_LOGOUT_PARAMETER_NAME));
            HANDLER.setRelayStateParameterName(getPropertyFromInitParams(filterConfig, "relayStateParameterName",
                    SingleSignOutHandler.DEFAULT_RELAY_STATE_PARAMETER_NAME));
            HANDLER.setCasServerUrlPrefix(getPropertyFromInitParams(filterConfig, "casServerUrlPrefix", ""));
            HANDLER.setArtifactParameterOverPost(parseBoolean(getPropertyFromInitParams(filterConfig,
                    "artifactParameterOverPost", "false")));
            HANDLER.setEagerlyCreateSessions(parseBoolean(getPropertyFromInitParams(filterConfig,
                    "eagerlyCreateSessions", "true")));
        }
        HANDLER.init();
        handlerInitialized.set(true);
	}
	
	public void setArtifactParameterName(String artifactParameterName) {  
		this.artifactParameterName = artifactParameterName;
		HANDLER.setArtifactParameterName(artifactParameterName);
	}

    public void setLogoutParameterName(final String name) {
        HANDLER.setLogoutParameterName(name);
    }

    public void setFrontLogoutParameterName(final String name) {
        HANDLER.setFrontLogoutParameterName(name);
    }

    public void setRelayStateParameterName(final String name) {
        HANDLER.setRelayStateParameterName(name);
    }

    public void setCasServerUrlPrefix(final String casServerUrlPrefix) {
        HANDLER.setCasServerUrlPrefix(casServerUrlPrefix);
    }

    public void setSessionMappingStorage(final SessionMappingStorage storage) {
        HANDLER.setSessionMappingStorage(storage);
    }

    protected static SingleSignOutHandler getSingleSignOutHandler() {
        return HANDLER;
    }
    
	public void init() {  
		CommonUtils.assertNotNull(this.artifactParameterName, "artifactParameterName cannot be null.");  
		CommonUtils.assertNotNull(SESSION_MAPPING_STORAGE, "sessionMappingStorage cannote be null.");  
	} 

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("DemoCustomer CustomSingleSignOutFilter");
		if (!this.handlerInitialized.getAndSet(true)) {
            HANDLER.init();
        }
		
		final HttpServletRequest request = (HttpServletRequest) servletRequest;     
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
	    final String logoutRequest = CommonUtils.safeGetParameter(request, "logoutRequest");     
	    Enumeration ff = request.getParameterNames();     
	    String a = request.getQueryString();     
	    if (CommonUtils.isNotBlank(logoutRequest)) {     
	         final String sessionIdentifier = XmlUtils.getTextForElement(logoutRequest, "SessionIndex");     
	  
	         if (CommonUtils.isNotBlank(sessionIdentifier)) {     
	            final HttpSession session = SESSION_MAPPING_STORAGE.removeSessionByMappingId(sessionIdentifier);     
	  
	            if (session != null) {     
	                 String sessionID = session.getId();                        
	                 try {     
	                	SessionInformation si = sessionRegistry.getSessionInformation(sessionID);
	     	            sessionRegistry.removeSessionInformation(sessionID);
	                    session.invalidate();     
	                 } catch (final IllegalStateException e) {     
	                         
	                 }     
	            }     
	         }     
	     }else {     
	        final String artifact = CommonUtils.safeGetParameter(request, this.artifactParameterName);     
	        final HttpSession session = request.getSession(false);     
	             
	        if (CommonUtils.isNotBlank(artifact) && session!=null) {     
	            try {     
	                SESSION_MAPPING_STORAGE.removeBySessionById(session.getId());     
	            } catch (final Exception e) {     
	                     
	            }     
	            SESSION_MAPPING_STORAGE.addSessionById(artifact, session);     
	        }     
	    }
	    
        if (HANDLER.process(request, response)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

	  
//	    filterChain.doFilter(servletRequest, servletResponse);		
	}

	@Override
	public void destroy() {

	}

	public static SessionMappingStorage getSESSION_MAPPING_STORAGE() {
		return SESSION_MAPPING_STORAGE;
	}

	public static void setSESSION_MAPPING_STORAGE(SessionMappingStorage sESSION_MAPPING_STORAGE) {
		SESSION_MAPPING_STORAGE = sESSION_MAPPING_STORAGE;
	}

}
