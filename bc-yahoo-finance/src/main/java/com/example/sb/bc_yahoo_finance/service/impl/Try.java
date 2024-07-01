package com.example.sb.bc_yahoo_finance.service.impl;


// @Service
public class Try {  //
// implements StockDataService{

//   @Value("${api.query1-finance-yahoo.domain}")
//   private String domain;

//   @Value("${api.query1-finance-yahoo.endpoints}")
//   private List<String> userEndpoints;

//   @Autowired
//   private RestTemplate restTemplate;

//   @Autowired
//   private StockDataRepository stockDataRepository;

//   @Autowired
//   private ResultEntityMapper resultEntityMapper;
  
//   private final static Logger log = LoggerFactory.getLogger(Try.class);

//   public static String crumb = null; 
//   public static String cookie = null; 

//   private static void setCookie() {
//     try {
//       URL url = new URI("https://fc.yahoo.com").toURL();
//       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//       // Get the cookie from the response headers
//       cookie = connection.getHeaderField("Set-Cookie");
//       connection.disconnect();
//     } catch (Exception e) {
//       log.debug("Failed to set cookie from http request. Intraday quote requests will most likely fail.", e);
//     }
//   }

//   private static void setCrumb() {
//     StringBuilder response = new StringBuilder();

//     try {
//       URL url = new URI("https://query1.finance.yahoo.com/v1/test/getcrumb").toURL(); 
//       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//       // Set the cookie
//       connection.setRequestProperty("Cookie", cookie);
//       connection.addRequestProperty("User-Agent", GlobalConstants.USER_AGENT);

//       // Make the HTTP request
//       connection.setRequestMethod("GET");

//       // Read the response content
//       try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//         String line;
//         while ((line = reader.readLine()) != null) {
//           response.append(line);
//         }
//       }
//     } catch (Exception e) {
//       log.debug("Failed to set crumb from http request. Intraday quote requests will most likely fail.", e);
//     }
//     crumb = response.toString();
//   }

//   public static synchronized void resetCookieCrumb() {
//     setCookie();
//     setCrumb();
//   }

//   public static synchronized String getCookie() {
//     if (cookie == null || cookie.isEmpty()) {
//       resetCookieCrumb();
//     }
//     return cookie;
//   }

//   public static synchronized String getCrumb() {
//     if (crumb == null || crumb.isBlank()) {
//       resetCookieCrumb();
//     }
//     return crumb;
//   }

//   @Override
//   public List<Response> getResponse() throws JsonMappingException, JsonProcessingException {
//     String crumb = getCrumb();
//     // System.out.println("Crumb="+ crumb);
//     String cookie = getCookie();
//     // System.out.println("Cookie="+ cookie);
//     List<Response> responses = new ArrayList<>();
//     for (String endpoint : userEndpoints){

//     String url = UriComponentsBuilder.newInstance()
//       .scheme(Scheme.HTTPS.lowerCase())
//       .host(this.domain)
//       .path(endpoint)
//       .queryParam("crumb", crumb)
//       .toUriString();      // String.format("https://query1.finance.yahoo.com/v7/finance/quote?symbols=0388.HK&crumb=%s", crumb);
//         // Create HttpHeaders object to set the Cookie and User-Agent headers
//         HttpHeaders headers = new HttpHeaders();
//         headers.set("Cookie", cookie);
//         headers.set("User-Agent", GlobalConstants.USER_AGENT);          
//         // Create an HttpEntity with the headers
//         HttpEntity<String> entity = new HttpEntity<>(headers);
//         // Log the request details
//         // Make the request with headers and capture the response
//         ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//         String responseBody = responseEntity.getBody();
//         // Extract the body from the response
//         ObjectMapper objectMapper = new ObjectMapper();
//         Response response = objectMapper.readValue(responseBody, Response.class); 
//         // Return the response as a list
//         responses.add(response);
//     }
//     return responses;
    
// }
//    @Override
//    public void saveStockData()throws JsonMappingException, JsonProcessingException{
//       List<Response> responses = getResponse();
//       // List<ResultEntityDTO> dtoList = new ArrayList<>();
//     for (Response response : responses) {
//         QuoteResponse quoteResponse = response.getQuoteResponse();
//         if (quoteResponse != null) {
//             Result[] results = quoteResponse.getResult();
//             if (results != null) {
//                      Arrays.stream(results)
//                     .map(r -> resultEntityMapper.map(r)) 
//                     .forEach(r -> stockDataRepository.save(r));
//                 // stockDataRepository.saveAllAndFlush(dto);  //map as Entity.
//             }
//         }
//      }
//    }
//    @Override
//    public void deleteData(){
//     List<ResultEntity> result = stockDataRepository.findAll();
//       if (result.size() > 0){
//         stockDataRepository.deleteAll();
//       }
//    }
}
  // public List<Response> getResponse() {
  //       String crumb = getCrumb();
  //       System.out.println(crumb);
  //       String cookie = getCookie();
  //       System.out.println(cookie);
  //       String url = String.format("https://query1.finance.yahoo.com/v7/finance/quote?symbols=0388.HK&crumb=%s", crumb);

  //       try {
  //           // HttpHeaders headers = new HttpHeaders();
  //           // headers.set("Cookie", cookie);
  //           // headers.set("User-Agent", GlobalConstants.USER_AGENT);
  //           Response[] response = restTemplate.getForObject(url, Response[].class);
  //           return Arrays.asList(response);
  //       } catch (Exception e) {
  //           log.error("Failed to get response from Yahoo Finance", e);
  //           return null;
  //       }
  //   }

