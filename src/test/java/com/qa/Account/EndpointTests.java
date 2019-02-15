package com.qa.Account;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.qa.user.userapi.persistence.domain.User;
import com.qa.user.userapi.persistence.domain.Email;
import com.qa.user.userapi.persistence.domain.SentEmail;
import com.qa.user.userapi.rest.UserRest;
import com.qa.user.userapi.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class EndpointTests {

	@InjectMocks
	UserRest rest;
	
	@Mock
	UserService service;
	
	@Mock
	RestTemplate restTemp;
	
	private static final String MOCK_ACCOUNT_NUMBER = "A:453893";
	private static final User MOCK_ACCOUNT_1 = new User(1L, "Ben", "Taylor", "C:634893");
	private static final User MOCK_ACCOUNT_2 = new User(2L, "Alvin", "Joseph", "B:749452");
	private static final Email MOCK_PRIZE = new Email();
	private static final SentEmail MOCK_SENT_PRIZE = new SentEmail();
	private static final ResponseEntity<Object> MOCK_RESPONSE_ENTITY = new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	private static final String MOCK_URL = "http://mock.com";
	
	@Test
	public void getAccountsTest() {
		List<User> MOCK_LIST = new ArrayList<>();;
		MOCK_LIST.add(MOCK_ACCOUNT_1);
		MOCK_LIST.add(MOCK_ACCOUNT_2);
		Mockito.when(service.getAccounts()).thenReturn(MOCK_LIST);
		assertEquals(MOCK_LIST, rest.getAccounts());
		Mockito.verify(service).getAccounts();
	}
	
	@Test
	public void getAccountTest() {
		Mockito.when(service.getAccount(1L)).thenReturn(MOCK_ACCOUNT_1);
		assertEquals(MOCK_ACCOUNT_1, rest.getAccount(1L));
		Mockito.verify(service).getAccount(1L);
	}
	
	@Test
	public void deleteAccountTest() {
		Mockito.when(service.deleteAccount(1L)).thenReturn(MOCK_RESPONSE_ENTITY);
		assertEquals(MOCK_RESPONSE_ENTITY, rest.deleteAccount(1L));
		Mockito.verify(service).deleteAccount(1L);
	}
	
	@Test
	public void updateAccountTest() {
		Mockito.when(service.updateAccount(MOCK_ACCOUNT_1, 1L)).thenReturn(MOCK_RESPONSE_ENTITY);
		assertEquals(MOCK_RESPONSE_ENTITY, rest.updateAccount(MOCK_ACCOUNT_1, 1L));
		Mockito.verify(service).updateAccount(MOCK_ACCOUNT_1, 1L);
	}
	
	@Test
	@Ignore
	public void createAccountTest() {
		Mockito.when(restTemp.getForObject(MOCK_URL, String.class)).thenReturn(MOCK_ACCOUNT_NUMBER);
		Mockito.when(restTemp.getForObject(MOCK_URL, Email.class)).thenReturn(MOCK_PRIZE);
		Mockito.when(service.addAccount(MOCK_ACCOUNT_1)).thenReturn(MOCK_ACCOUNT_1);

		assertEquals(MOCK_ACCOUNT_1, rest.createAccount(MOCK_ACCOUNT_1));
		Mockito.verify(service).addAccount(MOCK_ACCOUNT_1);
		Mockito.verify(restTemp).getForObject(MOCK_URL, String.class);
		Mockito.verify(restTemp).getForObject(MOCK_URL, Email.class);
	}
	
}
