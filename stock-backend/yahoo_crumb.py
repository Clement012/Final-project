import requests
import logging

# Configure logging
logging.basicConfig(level=logging.DEBUG)
log = logging.getLogger("RealTimeService")

class YahooConnector:
    crumb = None
    cookie = None

    @classmethod
    def set_cookie(cls):
        try:
            url = "https://fc.yahoo.com"
            response = requests.get(url)
            cls.cookie = response.headers.get("Set-Cookie")
        except Exception as e:
            log.debug("Failed to set cookie from http request. Intraday quote requests will most likely fail.", exc_info=e)

    @classmethod
    def set_crumb(cls):
        response_text = ""
        try:
            url = "https://query1.finance.yahoo.com/v1/test/getcrumb"
            headers = {
                "Cookie": cls.cookie or "",
                "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36"
            }
            response = requests.get(url, headers=headers)
            response.raise_for_status()
            response_text = response.text.strip()
        except Exception as e:
            log.debug("Failed to set crumb from http request. Intraday quote requests will most likely fail.", exc_info=e)
        cls.crumb = response_text

    @classmethod
    def reset_cookie_crumb(cls):
        cls.set_cookie()
        cls.set_crumb()

    @classmethod
    def get_cookie(cls):
        if not cls.cookie:
            cls.reset_cookie_crumb()
        return cls.cookie

    @classmethod
    def get_crumb(cls):
        if not cls.crumb:
            cls.reset_cookie_crumb()
        return cls.crumb

    @classmethod
    def print_crumb(cls):
        if not cls.crumb:
            cls.get_crumb()
        print('crumb: ' + cls.crumb)  # Print crumb as requested

if __name__ == "__main__":
    YahooConnector.print_crumb()