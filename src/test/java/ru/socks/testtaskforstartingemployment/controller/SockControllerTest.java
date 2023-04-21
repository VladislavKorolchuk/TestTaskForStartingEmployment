package ru.socks.testtaskforstartingemployment.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import ru.socks.testtaskforstartingemployment.model.Sock;
import ru.socks.testtaskforstartingemployment.model.Сoming;
import ru.socks.testtaskforstartingemployment.model.Сonsumption;
import ru.socks.testtaskforstartingemployment.repository.SockRepository;
import ru.socks.testtaskforstartingemployment.repository.СomingRepository;
import ru.socks.testtaskforstartingemployment.repository.СonsumptionRepository;
import ru.socks.testtaskforstartingemployment.service.SockService;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Transactional
public class SockControllerTest {

    @MockBean
    private SockService sockService;
    @Autowired
    private SockController sockController;
    @Autowired
    private SockRepository sockRepository;
    @Autowired
    private СomingRepository comingRepository;
    @Autowired
    private СonsumptionRepository consumptionRepository;

    @Autowired
    MockMvc mockMvc;


    @Test
    void contextLoads() {
        Assertions.assertThat(sockController).isNotNull();
    }

    @Test
    void testAddSock() throws Exception {

        sockRepository.save(new Sock(1L, "Red", 90));
        Optional <Sock> sock = sockRepository.findByColor("Red");
        assert (sock.get().getColor().equals("Red"));
        assert (sock.get().getCottonPart()==90);
        comingRepository.save(new Сoming(1L, 5, sock.get()));
        consumptionRepository.save(new Сonsumption(1L, 5, sock.get()));
        List<Сoming> comingList= comingRepository.findAllById(1L);
        assert (comingList.size()>0);
        List<Сonsumption> consumptionList= consumptionRepository.findAllById(1L);
        assert (consumptionList.size()>0);
    }

}
