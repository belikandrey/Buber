package com.epam.jwd.command;

import com.epam.jwd.command.impl.*;

public enum CommandType {
    TO_HOME(new ToHomeCommand()),
    TO_CLIENT_COMMON(new ToClientCommonCommand()),
    TO_DRIVER_COMMON(new ToDriverCommonCommand()),
    TO_ADMIN_LOGIN(new ToAdminLoginCommand()),
    CLIENT_LOGIN(new ClientLoginCommand()),
    TO_CLIENT_HOME(new ToClientHomeCommand()),
    DRIVER_LOGIN(new DriverLoginCommand()),
    TO_DRIVER_HOME(new ToDriverHomeCommand()),
    ADMIN_LOGIN(new AdminLoginCommand()),
    TO_ADMIN_HOME(new ToAdminHomeCommand()),
    TO_CLIENT_SIGNIN(new ToClientSigninCommand()),
    TO_DRIVER_SIGNIN(new ToDriverSigninCommand()),
    CLIENT_SIGNIN(new ClientSigninCommand()),
    DRIVER_SIGNIN(new DriverSigninCommand()),
    TO_CLIENT_VERIFY_EMAIL(new ToClientVerifyEmailCommand()),
    CLIENT_VERIFY_EMAIL(new ClientVerifyEmailCommand()),
    TO_CLIENT_FORGOT_PASSWORD(new ToClientForgotPasswordCommand()),
    CLIENT_FORGOT_PASSWORD(new ClientForgotPasswordCommand()),
    TO_DRIVER_VERIFY_EMAIL(new ToDriverVerifyEmailCommand()),
    DRIVER_VERIFY_EMAIL(new DriverVerifyEmailCommand()),
    TO_DRIVER_FORGOT_PASSWORD(new ToDriverForgotPasswordCommand()),
    DRIVER_FORGOT_PASSWORD(new DriverForgotPasswordCommand()),
    TO_UPDATE_DRIVER_INFO(new ToUpdateDriverInfoCommand()),
    UPDATE_DRIVER_INFO(new UpdateDriverInfoCommand()),
    TO_UPDATE_CLIENT_INFO(new ToUpdateClientInfoCommand()),
    UPDATE_CLIENT_INFO(new UpdateClientInfoCommand()),
    TO_ADD_ADMIN(new ToAddAdminCommand()),
    ADD_ADMIN(new AddAdminCommand()),
    TO_SHOW_ALL_RIDES(new ToShowAllRidesCommand()),
    TO_SHOW_ALL_PAYMENTS(new ToShowAllPaymentsCommand()),
    TO_CONFIRM_DRIVER(new ToConfirmDriverCommand()),
    CONFIRM_DRIVER(new ConfirmDriverCommand()),
    TO_CREATE_RIDE(new ToCreateRideCommand()),
    CREATE_RIDE(new CreateRideCommand()),
    TO_CREATE_RIDE_SUBMIT(new ToCreateRideSubmitCommand()),
    CLIENT_SUBMIT_RIDE(new ClientSubmitCommand()),
    TO_CLIENT_SHOW_RIDE_HISTORY(new ToClientShowRideHistoryCommand()),
    TO_ADD_BANK_CARD(new ToAddBankCardCommand()),
    ADD_BANK_CARD(new AddBankCardCommand()),
    TO_UPDATE_CLIENT_PHONE_NUMBER(new ToUpdateClientPhoneNumberCommand()),
    UPDATE_CLIENT_PHONE_NUMBER(new UpdateClientPhoneNumberCommand()),
    TO_EDIT_CLIENT_PASSWORD(new ToEditClientPasswordCommand()),
    EDIT_CLIENT_PASSWORD(new EditClientPasswordCommand()),
    TO_AVAILABLE_RIDES(new ToAvailableRidesCommand()),
    TO_DRIVER_SUBMIT_RIDE(new ToDriverSubmitRideCommand()),
    TO_END_DRIVER_RIDE(new ToEndDriverRideCommand()),
    DRIVER_END_RIDE(new DriverEndRideCommand()),
    TO_UPDATE_DRIVER_PHONE_NUMBER(new ToUpdateDriverPhoneNumberCommand()),
    TO_ADD_CAR(new ToAddCarCommand()),
    TO_EDIT_DRIVER_PASSWORD(new ToEditDriverPasswordCommand()),
    UPDATE_DRIVER_PHONE_NUMBER(new UpdateDriverPhoneNumberCommand()),
    EDIT_DRIVER_PASSWORD(new EditDriverPasswordCommand()),
    ADD_CAR(new AddCarCommand()),
    UPDATE_DRIVER_MARK(new UpdateDriverMarkCommand()),
    LOGOUT(new LogoutCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
