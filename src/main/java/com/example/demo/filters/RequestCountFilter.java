package com.example.demo.filters;

import jakarta.servlet.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class RequestCountFilter implements Filter {

    private final AtomicLong requestCount = new AtomicLong(0);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        requestCount.incrementAndGet();
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public Long getRequestCount() {
        return requestCount.get();
    }
}
