package com.alibaba.fescar.core.context;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fescar.common.loader.LoadLevel;

/**
 * The type Thread local context core.
 */
@LoadLevel(name = "ThreadLocalContextCore", order = Integer.MIN_VALUE)
public class ThreadLocalContextCore implements ContextCore {

	private ThreadLocal<Map<String, String>> threadLocal = new InheritableThreadLocal<Map<String, String>>() {
	    @Override
	    protected Map<String, String> initialValue() {
	        return new HashMap<String, String>();
	    }

	};

    @Override
    public String put(String key, String value) {
        return threadLocal.get().put(key, value);
    }

    @Override
    public String get(String key) {
        return threadLocal.get().get(key);
    }

    @Override
    public String remove(String key) {
        return threadLocal.get().remove(key);
    }
}
