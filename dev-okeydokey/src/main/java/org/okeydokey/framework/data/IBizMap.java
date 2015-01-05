package org.okeydokey.framework.data;

import java.io.Serializable;
import java.math.BigDecimal;

public interface IBizMap extends Serializable {

	public Object get(String key);

	public String getString(String key);
	
	public int getInt(String key);
	
	public long getLong(String key);
	
	public double getDouble(String key);
	
	public float getFloat(String key);
	
	public BigDecimal getBigDecimal(String key);
	
}
