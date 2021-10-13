package com.example.bankaccount.common.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class AccountIdUTest {

    @Nested
    class ConstructorShould {

        @Test
        void throwMissingAccountIdExceptionWhenValueIsNull() {
            Throwable throwable = catchThrowable(() -> new AccountId(null));

            assertThat(throwable).isInstanceOf(MissingAccountIdException.class);
        }
    }

    @Nested
    class EqualsShould {

        @Test
        void return_true_when_value_is_equal() {

            final Integer AccountIdValue = 1234;
            AccountId accountId1 = new AccountId(AccountIdValue);
            AccountId accountId2 = new AccountId(AccountIdValue);;

            boolean result = accountId1.equals(accountId2);

            assertThat(result).isTrue();
        }

        @Test
        void return_false_when_value_is_not_equal() {

            AccountId accountId1 = new AccountId(11);
            AccountId accountId2= new AccountId(12);

            boolean result = accountId1.equals(accountId2);

            assertThat(result).isFalse();
        }
    }
    @Nested
    class toString {

        @Test
        void return_the_string_representation_of_account_id() {
            Integer AccountIdValue = 123;
            AccountId AccountId = new AccountId(AccountIdValue);

            String result = AccountId.toString();

            assertThat(result).isEqualTo(AccountIdValue.toString());
        }
    }
}