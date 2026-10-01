package vn.edu.ktqt.business;

import vn.edu.ktqt.data.UserDAO_24110179;
import vn.edu.ktqt.model.User_24110179;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.Random;

public class AuthService_24110179 {
    private final UserDAO_24110179 userDAO = new UserDAO_24110179();
    private final MailService_24110179 mailService = new MailService_24110179();

    public User_24110179 login(String email, String password) throws SQLException {
        User_24110179 user = userDAO.findByEmailAndPassword(email, password);
        if (user != null) {
            userDAO.updateLastLogin(user.getId());
        }
        return user;
    }

    public String startRegister(String email) throws SQLException, MessagingException {
        if (userDAO.existsByEmail(email)) {
            throw new IllegalArgumentException("Email đã tồn tại");
        }
        String otp = String.format("%06d", new Random().nextInt(1_000_000));
        mailService.sendOtp(email, otp);
        return otp;
    }

    public void createUser(String email, String fullname, String phone, String password) throws SQLException {
        userDAO.createUser(email, fullname, phone, password);
    }
}
