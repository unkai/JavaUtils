/**
 *
 */
package jp.mydns.liquor.utils;

/**
 * @author h.taga<hypzwm@gmail.com>
 *
 */
public class UserAgent {

	private static String getSafariVersion(String s) {
		int f = s.indexOf("Safari/") + 7;
		int t = s.indexOf(".", f);
		if (t < 0) t = s.length();
		return s.substring(f, t);
	}

	private static String getChromeVersion(String s) {
		int f = s.indexOf("Chrome/") + 7;
		int t = s.indexOf(".", f);
		if (t < 0) t = s.length();
		return s.substring(f, t);
	}

	private static String getOperaVersion(String s) {
		int f = s.indexOf("Opera/") + 6;
		int t = s.indexOf(".", f);
		if (t < 0) t = s.length();
		return s.substring(f, t);
	}

	private static String getFirefoxVersion(String s) {
		int f = s.indexOf("Firefox/") + 8;
		int t = s.indexOf(".", f);
		if (t < 0) t = s.length();
		return s.substring(f, t);
	}

	private static String getMSIEVersion(String s) {
		int f = s.indexOf("MSIE ") + 5;
		int t = s.indexOf(".", f);
		if (t < 0) t = s.length();
		return s.substring(f, t);
	}

	private static String getMozillaVersion(String s) {
		int f = s.indexOf("Mozilla/") + 8;
		int t = s.indexOf(".", f);
		if (t < 0) t = s.length();
		return s.substring(f, t);
	}

	public static UserAgent analyze(String s) {
		UserAgent ua = new UserAgent();
		if (s.indexOf("iPad") >= 0) {
			ua.model = "iPad";
			ua.os = "iOS";
			ua.osVersion = "3";
			if (s.indexOf("OS 4_") >= 0) {
				ua.osVersion = "4";
			} else if (s.indexOf("OS 5_") >= 0) {
				ua.osVersion = "5";
			} else if (s.indexOf("OS 6_") >= 0) {
				ua.osVersion = "6";
			}
			ua.browser = "Safari";
			if (s.indexOf("Mobile/7B405") >= 0) {
				ua.browserVersion = "531";
			} else if (s.indexOf("Mobile/8C134") >= 0) {
				ua.browserVersion = "531";
			}
		} else if (s.indexOf("iPod") >= 0) {
			ua.model = "iPod";
			ua.os = "iOS";
			ua.osVersion = "2";
			if (s.indexOf("OS 3_") >= 0) {
				ua.osVersion = "3";
			} else if (s.indexOf("OS 4_") >= 0) {
				ua.osVersion = "4";
			} else if (s.indexOf("OS 5_") >= 0) {
				ua.osVersion = "5";
			} else if (s.indexOf("OS 6_") >= 0) {
				ua.osVersion = "6";
			}
			ua.browser = "Safari";
		} else if (s.indexOf("iPhone") >= 0) {
			ua.model = "iPhone";
			ua.os = "iOS";
			ua.osVersion = "1";
			if (s.indexOf("OS 2_") >= 0) {
				ua.osVersion = "2";
			} else if (s.indexOf("OS 3_") >= 0) {
				ua.osVersion = "3";
			} else if (s.indexOf("OS 4_") >= 0) {
				ua.osVersion = "4";
			} else if (s.indexOf("OS 5_") >= 0) {
				ua.osVersion = "5";
			} else if (s.indexOf("OS 6_") >= 0) {
				ua.osVersion = "6";
			}
			ua.browser = "Safari";
			if (s.indexOf("Mobile/8L1") >= 0) {
				ua.browserVersion = "6533";
			}
		} else if (s.indexOf("Android") >= 0) {
			ua.model = "Android";
			ua.os = "Android";
			ua.osVersion = "";
			if (s.indexOf("Android 1.") >= 0) {
				ua.osVersion = "1";
			} else if (s.indexOf("Android 2.") >= 0) {
				ua.osVersion = "2";
			} else if (s.indexOf("Android 3.") >= 0) {
				ua.osVersion = "3";
			} else if (s.indexOf("Android 4.") >= 0) {
				ua.osVersion = "4";
			} else if (s.indexOf("Android 5.") >= 0) {
				ua.osVersion = "5";
			} else if (s.indexOf("Android 6.") >= 0) {
				ua.osVersion = "6";
			}
		} else if (s.indexOf("Mac_PowerPC") >= 0) {
			ua.model = "PC";
			ua.os = "Macintosh";
		} else if (s.indexOf("Macintosh") >= 0) {
			ua.model = "PC";
			ua.os = "Macintosh";
			ua.browser = "Netscape";
		} else if (s.indexOf("Win32") >= 0) {
			ua.model = "PC";
			ua.os = "Windows";
		} else if (s.indexOf("Win95") >= 0) {
			ua.model = "PC";
			ua.os = "Windows";
			ua.browser = "Firefox";
		} else if (s.indexOf("Win98") >= 0) {
			ua.model = "PC";
			ua.os = "Windows";
			ua.browser = "Firefox";
		} else if (s.indexOf("WinNT") >= 0) {
			ua.model = "PC";
			ua.os = "Windows";
			ua.browser = "Firefox";
		} else if (s.indexOf("Windows") >= 0) {
			ua.model = "PC";
			ua.os = "Windows";
			ua.browser = "Firefox";
		} else if (s.indexOf("(en-us)") >= 0) {
			ua.model = "PC";
			ua.os = "Windows";
		} else if (s.indexOf("SunOS") >= 0) {
			ua.model = "PC";
			ua.os = "UNIX";
			ua.browser = "Netscape";
		} else if (s.indexOf("FreeBSD") >= 0) {
			ua.model = "PC";
			ua.os = "UNIX";
			ua.browser = "Netscape";
		} else if (s.indexOf("Linux") >= 0) {
			ua.model = "PC";
			ua.os = "LINUX";
			ua.browser = "Firefox";
		}
		if (s.indexOf("Chrome") >= 0) {
			ua.browser = "Chrome";
			ua.browserVersion = getChromeVersion(s);
		} else if (s.indexOf("Opera") >= 0) {
			ua.browser = "Opera";
			ua.browserVersion = getOperaVersion(s);
		} else if (s.indexOf("Safari") >= 0) {
			ua.browser = "Safari";
			ua.browserVersion = getSafariVersion(s);
		} else if (s.indexOf("Firefox") >= 0) {
			ua.browser = "Firefox";
			ua.browserVersion = getFirefoxVersion(s);
		} else if (s.indexOf("Netscape6") >= 0) {
			ua.browser = "Netscape";
			ua.browserVersion = "6";
		} else if (s.indexOf("Netscape/7") >= 0) {
			ua.browser = "Netscape";
			ua.browserVersion = "7";
		} else if (s.indexOf("MSIE") >= 0) {
			ua.browser = "IE";
			ua.browserVersion = getMSIEVersion(s);
		} else if (s.indexOf("Trident") >= 0) {
			ua.browser = "IE";
			ua.browserVersion = "11";
		} else if (ua.model.equals("PC")) {
			if (s.indexOf("Mozilla") >= 0) {
				ua.browser = "Netscape";
				ua.browserVersion = getMozillaVersion(s);
				if (ua.browserVersion.equals("5")) {
					ua.browserVersion = "7";
				}
			}
		}
		return ua;
	}

	private String model = null;
	private String os = null;
	private String osVersion = null;
	private String browser = null;
	private String browserVersion = null;

	private UserAgent() {
		model = "Another";
		os = "Another";
		osVersion = "";
		browser = "Another";
		browserVersion = "";
	}

	public String model() {
		return model;
	}

	public String os() {
		return os;
	}

	public String osVersion() {
		return osVersion;
	}

	public String browser() {
		return browser;
	}

	public String browserVersion() {
		return browserVersion;
	}

}
