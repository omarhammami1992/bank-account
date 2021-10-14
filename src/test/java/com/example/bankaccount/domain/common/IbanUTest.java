package com.example.bankaccount.domain.common;

import com.example.bankaccount.domain.common.Iban;
import com.example.bankaccount.domain.common.MissingIbanException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class IbanUTest {

    @Nested
    class ConstructorShould {

        @Test
        void throwMissingIbanExceptionWhenValueIsNull() {
            Throwable throwable = catchThrowable(() -> new Iban(null));

            assertThat(throwable).isInstanceOf(MissingIbanException.class);
        }
    }

    @Nested
    class EqualsShould {

        @Test
        void return_true_when_value_is_equal() {

            final String ibanValue = "FR000000000000000";
            Iban Iban1 = new Iban(ibanValue);
            Iban Iban2 = new Iban(ibanValue);

            boolean result = Iban1.equals(Iban2);

            assertThat(result).isTrue();
        }

        @Test
        void return_false_when_value_is_not_equal() {

            Iban iban1 = new Iban("FR111111111111111111");
            Iban iban2 = new Iban("FR2222222222222222222");

            boolean result = iban1.equals(iban2);

            assertThat(result).isFalse();
        }
    }

    @Nested
    class toString {

        @Test
        void return_value() {
            String ibanValue = "FR00000000000000";
            Iban Iban = new Iban(ibanValue);

            String result = Iban.toString();

            assertThat(result).isEqualTo(ibanValue.toString());
        }
    }
}