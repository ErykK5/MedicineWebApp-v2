package com.company.medprojectapp.bootstrapTest;

import com.company.medprojectapp.model.Category;
import com.company.medprojectapp.model.Medicine;
import com.company.medprojectapp.model.Producer;
import com.company.medprojectapp.repo.CategoryRepository;
import com.company.medprojectapp.repo.ComponentRepository;
import com.company.medprojectapp.repo.MedRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class MedBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final MedRepository medRepository;
    private final ComponentRepository componentRepository;

    public MedBootstrap(CategoryRepository categoryRepository, MedRepository medRepository, ComponentRepository componentRepository) {
        this.categoryRepository = categoryRepository;
        this.medRepository = medRepository;
        this.componentRepository = componentRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        medRepository.saveAll(getMed());
    }

    private List<Medicine> getMed() {

        List<Medicine> medicines = new ArrayList<>(3);

        Medicine apap = new Medicine();
        Medicine ibuprofen = new Medicine();
        Medicine rutinoscorbin = new Medicine();

        Producer apapProducer = new Producer();
        Producer ibuprofenProducer = new Producer();
        Producer rutinoscorbinProducer = new Producer();

        apap.setName("apap");
        ibuprofen.setName("ibuprofen");
        rutinoscorbin.setName("rutinoscorbin");

        apap.setURL("www.apap.com");
        ibuprofen.setURL("www.ibuprofen.com");
        rutinoscorbin.setURL("www.rutinoscorbin.com");

        apap.setDosage("The dose of acetaminophen for adults is 325 mg to 650 mg every 4 to 6 hours. Do not take more than 4,000 mg in a 24-hour period. If you are taking the extended-release caplets, the recommended dose for adults and children over 12 years is 1300 mg taken every 8 hours.");
        ibuprofen.setDosage("Doses greater than 40 mg/kg/day may increase risk of serious adverse effects; doses greater than 50 mg/kg/day have not been studied and are not recommended. With doses above 30 mg/kg/day or in patients with a history of abnormal liver function tests with previous NSAID therapy, closely monitor for signs/symptoms of early liver dysfunction. Therapeutic response may not be achieved for a few days or several weeks; the dosage should be lowered to the lowest effective dose once clinical effect is achieved.");
        rutinoscorbin.setDosage("This medication administered orally, IM, IV, intravaginally. For the prevention of deficiency conditions rutinoscorbin ) dose is 25-75 mg / day, for the treatment - 250 mg / day or more in divided doses. For intravaginal used rutinoscorbin (Vitamin C (Ascorbic Acid)) drugs in appropriate dosage forms.");

        medicines.add( apap );
        medicines.add( ibuprofen );
        medicines.add( rutinoscorbin );

        apapProducer.setProducerName("US Pharmacia");
        ibuprofenProducer.setProducerName("Polfarmex SA");
        rutinoscorbinProducer.setProducerName("GlaxoSmithKline");

        apap.setProducer( apapProducer );
        ibuprofen.setProducer( ibuprofenProducer );
        rutinoscorbin.setProducer( rutinoscorbinProducer );

        apapProducer.setMedicine( apap );
        ibuprofenProducer.setMedicine( ibuprofen );
        rutinoscorbinProducer.setMedicine( rutinoscorbin );

        apap.addComponent( "Paracetamol",500.0 );
        apap.addComponent( "kwas stearynowy",1.5 );
        apap.addComponent( "kroskarmeloza sodowa",1.3 );
        apap.addComponent( "skrobia żelowana",1.0 );
        apap.addComponent( "makrogol",2.0 );

        ibuprofen.addComponent( "Ibuprofen", 500.0 );
        ibuprofen.addComponent( "karboksymetyloskrobia sodowa", 1.0 );
        ibuprofen.addComponent( "celuloza mikrokrystaliczna", 5.0 );
        ibuprofen.addComponent( "skrobia zelowana kukurydziana", 0.3 );
        ibuprofen.addComponent( "powidon", 1.2 );

        rutinoscorbin.addComponent("kwas askorbowy", 500.0 );
        rutinoscorbin.addComponent("kwas stearynowy", 3.0 );
        rutinoscorbin.addComponent("kwas winowy", 0.3 );
        rutinoscorbin.addComponent("skrobia kukurydziana", 0.1 );
        rutinoscorbin.addComponent("sacharoza", 1.0 );


        Category headache = new Category("headache");
        Category muscle = new Category("muscle");
        Category aches = new Category("aches");
        Category arthritis = new Category("arthritis");
        Category backache = new Category("backache");
        Category toothache = new Category("toothache");
        Category cold = new Category("cold");
        Category fever = new Category("fever");
        categoryRepository.save( headache );
        categoryRepository.save( muscle );
        categoryRepository.save( aches );
        categoryRepository.save( arthritis );
        categoryRepository.save( backache );
        categoryRepository.save( toothache );
        categoryRepository.save( cold );
        categoryRepository.save( fever );

        apap.addCategotry( headache );
        apap.addCategotry( aches );
        apap.addCategotry( toothache );
        apap.addCategotry( arthritis );

        ibuprofen.addCategotry( muscle );
        ibuprofen.addCategotry( cold );
        ibuprofen.addCategotry( fever );
        ibuprofen.addCategotry( backache );

        rutinoscorbin.addCategotry( headache );
        rutinoscorbin.addCategotry( cold );
        rutinoscorbin.addCategotry( aches );

        return medicines;
    }
}
