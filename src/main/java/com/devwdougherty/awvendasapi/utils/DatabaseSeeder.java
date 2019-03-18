package com.devwdougherty.awvendasapi.utils;

import com.devwdougherty.awvendasapi.entities.Cliente;
import com.devwdougherty.awvendasapi.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DatabaseSeeder {

    @Autowired
    private ClienteRepository clienteRepository;

    public boolean alreadySetup = false;

    @EventListener
    @Transactional
    public void seed(ContextRefreshedEvent event) {
        try {
            if (alreadySetup)
                return;

            seedUsersTable();

            alreadySetup = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void seedUsersTable() throws ParseException {

        Cliente cliente = new Cliente();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        cliente.setId(1L);
        cliente.setCreatedAt(setDateNow());
        cliente.setUpdatedAt(setDateNow());
        cliente.setNome("Willian");
        clienteRepository.save(cliente);

        System.out.println("Users Seeded");
    }

    private Date setDateNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dateString = sdf.format(new Date());
        try {
            Date date = sdf.parse(dateString);
            return date;
        }catch (ParseException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
