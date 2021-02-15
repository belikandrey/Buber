package com.epam.jwd.command;


import com.epam.jwd.command.impl.*;

public enum CommandType {
    TO_HOME(new ToHomeCommand()),
    TO_JOIN(new ToJoinCommand()),
    TO_ADMIN_LOG_IN(new ToAdminLoginCommand()),
    TO_CLIENT_SIGN_IN(new ToClientSignInCommand()),
    TO_DRIVER_SIGN_IN(new ToDriverSignInCommand()),
    TO_FORGOT_PASSWORD(new ToForgotPasswordCommand()),
    LOG_IN(new LoginCommand()),
    FORGOT_PASSWORD(new ForgotPasswordCommand()),
    TO_VERIFY_EMAIL(new ToVerifyEmailCommand()),
    VERIFY_EMAIL(new VerifyEmailCommand()),
    CLIENT_SIGN_IN(new ClientSignInCommand()),
    TO_CLIENT_HOME(new ToClientHomeCommand()),
    CURRENT_PAYMENT(new CurrentPaymentCommand()),
    TO_CREATE_RIDE(new ToCreateRideCommand()),
    CREATE_RIDE(new CreateRideCommand()),
    TO_ADD_BANK_CARD(new ToAddBankCardCommand()),
    ADD_BANK_CARD(new AddBankCardCommand()),
    TO_UPDATE_CLIENT_PHONE(new ToUpdateClientPhoneCommand()),
    UPDATE_CLIENT_PHONE(new UpdateClientPhoneCommand()),
    TO_UPDATE_CLIENT_PASSWORD(new ToUpdateClientPasswordCommand()),
    UPDATE_CLIENT_PASSWORD(new UpdateClientPasswordCommand()),
    TO_SHOW_CLIENT_RIDES(new ToShowClientRidesCommand()),
    UPDATE_DRIVER_MARK(new UpdateDriverMarkCommand()),
    CLIENT_TO_SIGN_AS_DRIVER(new ClientToSignAsDriverCommand()),
    CLIENT_SIGN_AS_DRIVER(new ClientSignAsDriverCommand()),
    DRIVER_SIGN_IN(new DriverSignInCommand()),
    TO_DRIVER_HOME(new ToDriverHomeCommand()),
    CURRENT_CAR(new CurrentCarCommand()),
    TO_ADD_CAR(new ToAddCarCommand()),
    ADD_CAR(new AddCarCommand()),
    TO_UPDATE_DRIVER_PASSWORD(new ToUpdateDriverPasswordCommand()),
    UPDATE_DRIVER_PASSWORD(new UpdateDriverPasswordCommand()),
    TO_AVAILABLE_RIDES(new ToAvailableRidesCommand()),
    TO_DRIVER_SUBMIT_RIDE(new ToDriverSubmitRideCommand()),
    TO_END_RIDE(new ToEndRideCommand()),
    END_RIDE(new EndRideCommand()),
    LOGOUT(new LogoutCommand()),
    ADMIN_LOGIN(new AdminLoginCommand()),
    TO_ADMIN_HOME(new ToAdminHomeCommand()),
    TO_ADD_ADMIN(new ToAddAdminCommand()),
    TO_CONFIRM_DRIVER(new ToConfirmDriverCommand()),
    TO_SHOW_RIDES(new ToShowRidesCommand()),
    TO_SHOW_PAYMENTS(new ToShowPaymentsCommand()),
    TO_UPDATE_CLIENTS(new ToUpdateClientsCommand()),
    TO_UPDATE_DRIVERS(new ToUpdateDriversCommand()),
    ADD_ADMIN(new AddAdminCommand()),
    CONFIRM_DRIVER(new ConfirmDriverCommand()),
    UPDATE_CLIENT_STATUS(new UpdateClientStatusCommand()),
    UPDATE_CLIENT_RATING(new UpdateClientRatingCommand()),
    UPDATE_DRIVER_STATUS(new UpdateDriverStatusCommand()),
    UPDATE_DRIVER_RATING(new UpdateDriverRatingCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
