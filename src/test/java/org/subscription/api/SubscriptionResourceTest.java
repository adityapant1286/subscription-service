package org.subscription.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.subscription.api.entities.SubscriptionRequest;
import org.subscription.api.entities.SubscriptionResponse;
import org.subscription.api.validations.SubscriptionValidator;
import org.subscription.entities.payment.Card;
import org.subscription.service.SubscriptionCreatorService;
import org.subscription.service.TestConfig;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SubscriptionResource.class)
@WebMvcTest(controllers = SubscriptionResource.class)
@ContextConfiguration(classes = TestConfig.class)

public class SubscriptionResourceTest {

    private String user = "mockUser";
    private Calendar start;
    private Calendar end;

//    @Autowired
//    private TestRestTemplate rest;


    private MockMvc mvc;

    @InjectMocks
    private SubscriptionResource resource;

    @Autowired
    @Qualifier("subscriptionCreator")
    private SubscriptionCreatorService mockService;

    @Autowired
    private SubscriptionValidator validator;

    private String baseUrl;
    private String resourceUrl = "/subscriptions";

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(resource).build();
        baseUrl = "";
//        mockService = mock(SubscriptionCreatorService.class);
        start = Calendar.getInstance();
        end = Calendar.getInstance();

        start.set(2018, 1, 1);
        end.set(2018, 1, 28);
    }

    @After
    public void tearDown() {
        start = end = null;
    }

    @Test
    public void createSubscriptionTest() throws Exception {

        final List<String> mockList = Arrays.asList("06/02/2018", "13/02/2018", "20/02/2018", "27/02/2018");
//        final SubscriptionResponse resp = new SubscriptionResponse();
//        resp.setId(UUID.randomUUID());
//        resp.setAmount(new Card(new BigDecimal(20)));
//        resp.setInvoice_dates(mockList);
//        resp.setSubscription_type("WEEKLY");

        final SubscriptionRequest weeklyRequest = new SubscriptionRequest();
        weeklyRequest.setUser(user);
        weeklyRequest.setAmount(new BigDecimal(20));
        weeklyRequest.setFrequency("WEEKLY");
        weeklyRequest.setOccursOn("TUE");
        weeklyRequest.setStartDate(start.getTime());
        weeklyRequest.setEndDate(end.getTime());
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String requestJson = gson.toJson(weeklyRequest, SubscriptionRequest.class);

//        when(mockService.createSubscription(weeklyRequest)).thenReturn(resp);

//        final ResponseEntity<SubscriptionResponse> response = rest.postForEntity(baseUrl + resourceUrl, weeklyRequest, SubscriptionResponse.class);

//        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        mvc.perform(post(baseUrl + resourceUrl)
                        .content(requestJson)
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)).andDo(print())
            .andExpect(status().isCreated());
//
//        verify(mockService, times(1)).createSubscription(weeklyRequest);
//        verifyNoMoreInteractions(mockService);

//        Gson gson = new Gson();
//        String requestJson = gson.toJson(weeklyRequest, SubscriptionRequest.class);
//        List<String> mockList = Arrays.asList("06/02/2018", "13/02/2018", "20/02/2018", "27/02/2018");
//
//        String amountJson = gson.toJson(new Card(weeklyRequest.getAmount()));
//        String invoiceDatesJson = gson.toJson(mockList, List.class);
//
//        mvc.perform(post(baseUrl + resourceUrl)
//                        .content(requestJson)
//                        .contentType(APPLICATION_JSON)
//                        .accept(APPLICATION_JSON))
//            .andExpect(status().isCreated());
//            .andExpect(jsonPath("$.amount").value(amountJson))
//            .andExpect(jsonPath("$.subscription_type").value(weeklyRequest.getFrequency()))
//            .andExpect(jsonPath("$.invoice_dates").value(invoiceDatesJson));

//        final SubscriptionBuilder builder = new SubscriptionBuilder();
//        UUID uuid = UUID.randomUUID();
//        Subscription subscription = builder.id(uuid)
//                                        .paymentMethod(new Card(weeklyRequest.getAmount()))
//                                        .frequency(new Weekly(weeklyRequest.getOccursOn()))
//                                        .startDate(weeklyRequest.getStartDate())
//                                        .endDate(weeklyRequest.getEndDate())
//                                        .create();
//

//        final ResponseEntity<SubscriptionResponse> response = rest.withBasicAuth(user, "doe")
//                                                                        .postForEntity(BASE_URL, weeklyRequest, SubscriptionResponse.class);

//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//
//        final SubscriptionResponse resp = response.getBody();
//        assertEquals(subscription.getId(), resp.getId());
//        assertEquals(subscription.getPayment(), resp.getAmount());
//        assertEquals(subscription.getFrequency().getName(), resp.getSubscription_type());
//        assertEquals(mockList, resp.getInvoice_dates());
    }
}