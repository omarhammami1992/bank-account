package com.example.bankaccount.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountToSaveUTest {

    @Mock
    private UserInfo userInfo;

    @Nested
    class Create {
        final String firstName = "Omar";
        final String lastName = "Hammami";

        @Nested
        class WhenValidUserInfoShouldBind {

            @BeforeEach
            void setUp() {
                when(userInfo.getFirstName()).thenReturn(firstName);
                when(userInfo.getLastName()).thenReturn(lastName);
            }

            @Test
            void firstName() throws InvalidUserInfoException {
                // when
                AccountToSave accountToSave = AccountToSave.create(userInfo);

                // then
                assertThat(accountToSave.getFirstName()).isEqualTo(firstName);
            }

            @Test
            void lastName() throws InvalidUserInfoException {
                // when
                AccountToSave accountToSave = AccountToSave.create(userInfo);

                // then
                assertThat(accountToSave.getLastName()).isEqualTo(lastName);
            }

        }

        @Test
        void should_throw_InvalidUserInfoException_when_firstName_is_null() {
            // given
            when(userInfo.getFirstName()).thenReturn(null);

            // when
            final Throwable throwable = catchThrowable(() -> AccountToSave.create(userInfo));

            // then
            assertThat(throwable).isInstanceOf(InvalidUserInfoException.class);
        }

        @Test
        void should_throw_InvalidUserInfoException_when_lastName_is_null() {
            // given
            when(userInfo.getFirstName()).thenReturn(firstName);
            when(userInfo.getLastName()).thenReturn(null);

            // when
            final Throwable throwable = catchThrowable(() -> AccountToSave.create(userInfo));

            // then
            assertThat(throwable).isInstanceOf(InvalidUserInfoException.class);
        }
    }
}