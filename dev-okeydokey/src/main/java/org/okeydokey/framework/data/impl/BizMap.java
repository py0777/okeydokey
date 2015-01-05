package org.okeydokey.framework.data.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import org.okeydokey.framework.data.IBizMap;

public class BizMap<K, V> extends HashMap<String, Object> implements IBizMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4404140027117541522L;

	@Override
	public Object get(java.lang.String key) {

		if (key == null) {
			return null;
		}

		Object value = null;

		value = super.get(key);

		return value;
	}

	@Override
	public String getString(java.lang.String key) {
		if (key == null) {
			return "";
		}

		Object value = get(key);

		if (value == null) {
			return "";
		}

		return value.toString();
	}

	@Override
	public int getInt(java.lang.String key) {
		if (key == null) {
			return 0;
		}

		Object value = get(key);

		if (value == null) {
			return 0;
		}

		if (value instanceof java.lang.String) {
			try {
				return Integer.parseInt((String) value);
			} catch (NumberFormatException e) {
				return 0;
			}
		} else if (value instanceof java.lang.Number) {
			return ((Number) value).intValue();
		} else {
			return 0;
		}
	}

	@Override
	public long getLong(java.lang.String key) {
		if (key == null) {
			return 0;
		}

		Object value = get(key);

		if (value == null) {
			return 0;
		}

		if (value instanceof java.lang.String) {
			try {
				return Long.parseLong((String) value);
			} catch (NumberFormatException e) {
				return 0;
			}
		} else if (value instanceof java.lang.Number) {
			return ((Number) value).longValue();
		} else {
			return 0;
		}
	}

	@Override
	public double getDouble(java.lang.String key) {
		if (key == null) {
			return 0;
		}

		Object value = get(key);

		if (value == null) {
			return 0;
		}

		if (value instanceof java.lang.String) {
			try {
				return Double.parseDouble((String) value);
			} catch (NumberFormatException e) {
				return 0;
			}
		} else if (value instanceof java.lang.Number) {
			return ((Number) value).doubleValue();
		} else {
			return 0;
		}
	}

	@Override
	public float getFloat(java.lang.String key) {
		if (key == null) {
			return 0;
		}

		Object value = get(key);

		if (value == null) {
			return 0;
		}

		if (value instanceof java.lang.String) {
			try {
				return Float.parseFloat((String) value);
			} catch (NumberFormatException e) {
				return 0;
			}
		} else if (value instanceof java.lang.Number) {
			return ((Number) value).floatValue();
		} else {
			return 0;
		}
	}

	@Override
	public BigDecimal getBigDecimal(java.lang.String key) {

		if (key == null) {
			return new BigDecimal(0);
		}

		Object value = get(key);

		if (value == null) {
			return new BigDecimal(0);
		}

		if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		} else if (value instanceof Short || value instanceof Integer || value instanceof Long) {
			return BigDecimal.valueOf(((Number) value).longValue());
		} else if (value instanceof Float) {
			return new BigDecimal(String.valueOf(value));
		} else if (value instanceof Number) {
			return BigDecimal.valueOf(((Number) value).doubleValue());
		} else if (value instanceof String) {
			String valueStr = (String) value;
			try {
				return new BigDecimal(valueStr.trim());
			} catch (NumberFormatException e) {
				return new BigDecimal(0);
			}
		} else {
			return new BigDecimal(0);
		}

	}

	
}
