
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import ru.netology.UserModel;

import java.util.Locale;

import static java.lang.String.join;


public class GenerationUser {


    public static class Registration {
       private Registration() {
        }

            public static UserModel generateByDelivery (String Locale){
                Faker faker = new Faker(new Locale("ru"));
                FakeValuesService fakeValuesService = new FakeValuesService(new Locale("ru"), new RandomService());
                String userFullName = join(" ", faker.name().firstName(), faker.name().lastName());
                String userPhone = fakeValuesService.regexify("(\\+?)[1-9]\\d{10}");
                String cityPreInput = fakeValuesService.regexify("[оамсквт][оаеи]");
                return new UserModel(userFullName, userPhone,cityPreInput);

            }

        }

    }

