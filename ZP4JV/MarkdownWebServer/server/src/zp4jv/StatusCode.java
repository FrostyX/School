package zp4jv;

/**
 * @see http://en.wikipedia.org/wiki/List_of_HTTP_status_codes
 */

public enum StatusCode {

	// 1xx Informational
	Continue                      (100, "Continue"),
	SwitchingProtocols            (101, "Switching Protocols"),
	Processing                    (102, "Processing"),
	
	// 2xx Success
	OK                            (200, "OK"),
	Created                       (201, "Created"),
	Accepted                      (202, "Accepted"),
	NonAuthoritativeInformation   (203, "Non-Authoritative Information"),
	NoContent                     (204, "No Content"),
	ResetContent                  (205, "Reset Content"),
	PartialContent                (206, "Partial Content"),
	MultiStatus                   (207, "Multi-Status"),
	AlreadyReported               (208, "Already Reported"),
	IMUsed                        (226, "IM Used"),
	
	// 3xx Redirection
	MultipleChoices               (300, "Multiple Choices"),
	MovedPermanently              (301, "Moved Permanently"),
	Found                         (302, "Found"),
	SeeOther                      (303, "See Other"),
	NotModified                   (304, "Not Modified"),
	UseProxy                      (305, "Use Proxy"),
	SwitchProxy                   (306, "Switch Proxy"),
	TemporaryRedirect             (307, "Temporary Redirect"),
	PermanentRedirect             (308, "Permanent Redirect"),
	
	// 4xx Client Error
	BadRequest                    (400, "Bad Request"),
	Unauthorized                  (401, "Unauthorized"),
	PaymentRequired               (402, "Payment Required"),
	Forbidden                     (403, "Forbidden"),
	NotFound                      (404, "Not Found"),
	MethodNotAllowed              (405, "Method Not Allowed"),
	NotAcceptable                 (406, "Not Acceptable"),
	ProxyAuthenticationRequired   (407, "Proxy Authentication Required"),
	RequestTimeout                (408, "Request Timeout"),
	Conflict                      (409, "Conflict"),
	Gone                          (410, "Gone"),
	LengthRequired                (411, "Length Required"),
	PreconditionFailed            (412, "Precondition Failed"),
	RequestEntityTooLarge         (413, "Request Entity Too Large"),
	RequestURITooLong             (414, "Request-URI Too Long"),
	UnsupportedMediaType          (415, "Unsupported Media Type"),
	RequestedRangeNotSatisfiable  (416, "Requested Range Not Satisfiable"),
	ExpectationFailed             (417, "Expectation Failed"),
	ImTeapot                      (418, "I'm a teapot"),
	AuthenticationTimeout         (419, "Authentication Timeout"),
	EnhanceYourCalm               (420, "Enhance Your Calm"),
	UnprocessableEntity           (422, "Unprocessable Entity"),
	LockedWebDAV                  (423, "Locked"),
	FailedDependency              (424, "Failed Dependency"),
	UnorderedCollection           (425, "Unordered Collection"),
	UpgradeRequired               (426, "Upgrade Required"),
	PreconditionRequired          (428, "Precondition Required"),
	TooManyRequests               (429, "Too Many Requests"),
	RequestHeaderFieldsTooLarge   (431, "Request Header Fields Too Large"),
	LoginTimeout                  (440, "Login Timeout"),
	NoResponse                    (444, "No Response"),
	RetryWith                     (449, "Retry With"),
	UnavailableForLegalReasons    (451, "Unavailable For Legal Reasons"),
	RequestHeaderTooLarge         (494, "Request Header Too Large"),
	CertError                     (495, "Cert Error"),
	NoCert                        (496, "No Cert"),
	HttpToHttps                   (497, "HTTP to HTTPS"),
	ClientClosedRequest           (499, "Client Closed Request"),
	
	// 5xx Server Error
	InternalServerError           (500, "Internal Server Error"),
	NotImplemented                (501, "Not Implemented"),
	BadGateway                    (502, "Bad Gateway"),
	ServiceUnavailable            (503, "Service Unavailable"),
	GatewayTimeout                (504, "Gateway Timeout"),
	HTTPVersionNotSupported       (505, "HTTP Version Not Supported"),
	VariantAlsoNegotiates         (506, "Variant Also Negotiates"),
	InsufficientStorage           (507, "Insufficient Storage"),
	LoopDetected                  (508, "Loop Detected"),
	BandwidthLimitExceeded        (509, "Bandwidth Limit Exceeded"),
	NotExtended                   (510, "Not Extended"),
	NetworkAuthenticationRequired (511, "Network Authentication Required"),
	OriginError                   (520, "Origin Error"),
	WebServerIsDown               (521, "Web server is down"),
	ConnectionTimedOut            (522, "Connection timed out"),
	ProxyDeclinedRequest          (523, "Proxy Declined Request"),
	NetworkReadTimeoutError       (598, "Network read timeout error"),
	NetworkConnectTimeoutError    (599, "Network connect timeout error");
	

	private String title;
	private int code;

	StatusCode(int code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public int getCode() {
		return code;
	}
}
