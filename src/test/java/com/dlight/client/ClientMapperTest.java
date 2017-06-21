package com.dlight.client;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.dlight.server.spring.AppConfig;
import com.dlight.server.spring.DatabaseConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DatabaseConfig.class)
@WebAppConfiguration
public class ClientMapperTest {
	
	@Autowired
	ClientMapper clientMapper;
	
	@Test
	public void readClientTest(){
		Client client = clientMapper.readClient("넥슨");
		assertThat("넥슨", is(client.getCompanyName()));
		assertThat("1234", is(client.getCompanyName()));
	}
	
	@Test
	public void readAuthorityTest(){
		List<Integer> authorities = clientMapper.readAuthority("넥슨");
	    assertThat(authorities, hasItems(ClientAuth.ADMIN.getCode(), ClientAuth.USER.getCode()));
	    
	    authorities = clientMapper.readAuthority("abc");
	    assertThat(authorities, hasItems(ClientAuth.USER.getCode()));
	}

}
