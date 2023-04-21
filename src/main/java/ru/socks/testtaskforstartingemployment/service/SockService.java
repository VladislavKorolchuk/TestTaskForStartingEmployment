package ru.socks.testtaskforstartingemployment.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.socks.testtaskforstartingemployment.dto.SockDto;
import ru.socks.testtaskforstartingemployment.model.Sock;
import ru.socks.testtaskforstartingemployment.model.Сoming;
import ru.socks.testtaskforstartingemployment.model.Сonsumption;
import ru.socks.testtaskforstartingemployment.repository.SockRepository;
import ru.socks.testtaskforstartingemployment.repository.СomingRepository;
import ru.socks.testtaskforstartingemployment.repository.СonsumptionRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SockService {

    private final Logger logger = LoggerFactory.getLogger(SockService.class);
    private final SockRepository sockRepository;
    private final СomingRepository comingRepository;
    private final СonsumptionRepository consumptionRepository;

    /**
     * @param sockDto - dto input
     *                <br> Is used entity User {@link SockDto} </br>
     *                <br> Is used repository {@link SockRepository#save(Object)} </br>
     *                <br> Is used repository {@link СomingRepository#save(Object)} </br>
     */
    public void addSock(SockDto sockDto) {

        logger.info("method is - addSock");
        Sock sock = new Sock(0, sockDto.getColor(), sockDto.getCottonPart());

        Optional<Sock> sockFind = sockRepository.findByColorAndCottonPart(sockDto.getColor(), sockDto.getCottonPart());
        boolean sockFound = sockFind.isPresent();

        if (sockFound == false) {
            sockRepository.save(sock);
            Сoming coming = new Сoming(0, sockDto.getQuantity(), sock);
            comingRepository.save(coming);
        } else {
            Сoming coming = new Сoming(0, sockDto.getQuantity(), sockFind.get());
            comingRepository.save(coming);
        }

    }

    /**
     * @param sockDto - dto input
     *                <br> Is used entity User {@link SockDto} </br>
     *                <br> Is used repository {@link SockRepository#save(Object)} </br>
     *                <br> Is used repository {@link СonsumptionRepository#save(Object)} </br>
     */
    public void subtractSock(SockDto sockDto) {

        logger.info("method is - subtractSock");
        Sock sock = new Sock(0, sockDto.getColor(), sockDto.getCottonPart());

        Optional<Sock> sockFind = sockRepository.findByColorAndCottonPart(sockDto.getColor(), sockDto.getCottonPart());
        boolean sockFound = sockFind.isPresent();

        if (sockFound == true) {
            List<Сoming> sockList = comingRepository.findAllById(sockFind.get().getId());
            // receipt of the receipt amount
            int sum = sockList.stream().mapToInt(a -> a.getQuantity()).sum();
            // checking the amount of the expense should not be the amount of the receipt
            if (sockDto.getQuantity() <= sum) {
                Сonsumption consumption = new Сonsumption(0, sockDto.getQuantity(), sockFind.get());
                consumptionRepository.save(consumption);
            }
        }
    }

    /**
     * @param color      - color sock String
     * @param operation  - output sorting type (moreThan, lessThan, equal)
     * @param cottonPart <br> Is used repository {@link SockRepository#save(Object)} </br>
     *                   <br> Is used repository {@link СonsumptionRepository#save(Object)} </br>
     * @return a line for counting the number of socks depending on the request
     */
    public String getSock(String color, String operation, Integer cottonPart) {

        logger.info("method is - getSock");
        int coming = 0;
        int expenditure = 0;
        Optional<Sock> sockFind = sockRepository.findByColorAndCottonPart(color, cottonPart);
        boolean sockFound = sockFind.isPresent();
        if (operation.equals("moreThan")) {
            List<Sock> sockList = sockFind.stream().sorted(Comparator.comparing(a -> a.getCottonPart() >= cottonPart)).collect(Collectors.toList());
            for (int i = 0; i < sockList.size(); i++) {
                List<Сoming> comingList = comingRepository.findAllById(sockList.get(i).getId());
                coming = coming + comingList.stream().mapToInt(a -> a.getQuantity()).sum();
                List<Сonsumption> consumptionList = consumptionRepository.findAllById(sockList.get(i).getId());
                expenditure = expenditure + consumptionList.stream().mapToInt(a -> a.getQuantity()).sum();
                ;
                return "Общее количество " + sockFind.get().getColor() + " носков с долей хлопка более "
                        + cottonPart + " - " + (coming - expenditure);
            }
        } else if (operation.equals("lessThan")) {
            List<Sock> sockList = sockFind.stream().sorted(Comparator.comparing(a -> a.getCottonPart() < cottonPart)).collect(Collectors.toList());
            for (int i = 0; i < sockList.size(); i++) {
                List<Сoming> comingList = comingRepository.findAllById(sockList.get(i).getId());
                coming = coming + comingList.stream().mapToInt(a -> a.getQuantity()).sum();
                List<Сonsumption> consumptionList = consumptionRepository.findAllById(sockList.get(i).getId());
                expenditure = expenditure + consumptionList.stream().mapToInt(a -> a.getQuantity()).sum();
                return "Общее количество " + sockFind.get().getColor() + " носков с долей хлопка менее "
                        + cottonPart + " - " + (coming - expenditure);
            }
        } else if (operation.equals("equal")) {
            List<Сoming> comingList = comingRepository.findAllById(sockFind.get().getId());
            coming = coming + comingList.stream().mapToInt(a -> a.getQuantity()).sum();
            List<Сonsumption> consumptionList = consumptionRepository.findAllById(sockFind.get().getId());
            expenditure = expenditure + consumptionList.stream().mapToInt(a -> a.getQuantity()).sum();
            return "Общее количество " + sockFind.get().getColor() + " носков с долей хлопка менее "
                    + cottonPart + " - " + (coming - expenditure);
        }
        return "";
    }

}
