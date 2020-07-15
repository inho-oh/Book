package usedbookstore;

import usedbookstore.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
public class PolicyHandler{
    @Autowired BookRepository BookRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchaseCanceled_StatusManage(@Payload PurchaseCanceled purchaseCanceled){

        if(purchaseCanceled.isMe()){
            Book book = null;
            Optional<Book> optional = BookRepository.findById(purchaseCanceled.getBookid());
            if (optional.isPresent()) {
                book = optional.get();
                book.setId(purchaseCanceled.getBookid());
                book.setQty(book.getQty() != null ? book.getQty().intValue() + 1 : 0);
                BookRepository.save(book);
                System.out.println("##### listener wheneverPurchased_StatusManage : " + purchaseCanceled.toJson());
            } else
                System.out.println("##### listener wheneverPurchased_StatusManage : null ");
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPurchased_StatusManage(@Payload Purchased purchased){

        if(purchased.isMe()) {
            Book book = null;
            Optional<Book> optional = BookRepository.findById(purchased.getBookid());
            if (optional.isPresent()) {
                book = optional.get();
                book.setId(purchased.getBookid());
                book.setQty(book.getQty() != null ? book.getQty().intValue() - 1 : 0);
                BookRepository.save(book);
                System.out.println("##### listener wheneverPurchased_StatusManage : " + purchased.toJson());
            } else
                System.out.println("##### listener wheneverPurchased_StatusManage : null ");

        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverSold_Register(@Payload Sold sold){

        if(sold.isMe()){
            Book book = new Book();

            book.setName(sold.getBookname());
            book.setQty(sold.getQty());
            book.setPrice(sold.getPrice());
            BookRepository.save(book);
        }
    }

}
