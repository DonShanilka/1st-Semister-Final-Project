package lk.ijse.semisterfinal.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.semisterfinal.Tm.SalaryTm;
import lk.ijse.semisterfinal.dto.AddEmployeeDTO;
import lk.ijse.semisterfinal.dto.SalaryDTO;
import lk.ijse.semisterfinal.model.AddEmployeeModel;
import lk.ijse.semisterfinal.model.SalaryModel;
import lombok.Getter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SalaryController implements Runnable {

    public AnchorPane root;
    public DatePicker date;
    public ComboBox<String> comEmpId;
    public TextField lblName;
    public TextArea txtMsg;
    @Getter
    private String Msg;
    @Getter
    private String To;
    @Getter
    private String Subject;
    public TableColumn<?, ?> colId;
    public TableColumn<?, ?> colName;
    public TableColumn<?, ?> colDate;
    public TableColumn<?, ?> colSalary;
    public TableColumn<?, ?> colAction;
    public TextField salary;
    public TableView<SalaryTm> salaryTm;
    public TextField txtTo;
    public TextField txtSubject;
    public Text Sending;

    private ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

    public SalaryController(String msg, String s, String subject) {
        Msg = msg;
        To = s;
        Subject = subject;
    }

    public SalaryController() {
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public void setTo(String to) {
        To = to;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    @Override
    public String toString() {
        return "SalaryController{" +
                "Msg='" + Msg + '\'' +
                ", To='" + To + '\'' +
                ", Subject='" + Subject + '\'' +
                '}';
    }

    public void initialize() {
        date.setPromptText(String.valueOf(LocalDate.now()));
        loadEmployeeId();
        clearField();
        //tableListener();
        setCellValueFactory();
        loadAllSalary();
    }
    /*private void tableListener() {
        SalaryTm.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData((SalaryTm) newValue);

        });
    }*/

    /*private void setData(SalaryTm row) {
        comEmpId.setValue(row.getEmployeeId());
        lblName.setText(row.getEmployeeName());
        salary.setText(String.valueOf(row.getSalary()));
        date.setValue(row.getDate());

    }*/

    private void clearField() {
        comEmpId.setValue("");
        lblName.setText("");
        salary.setText("");
        //date.setValue(LocalDate.parse(""));

    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

    }

    public void AddSalaryOnAction(ActionEvent event) {
        double amount = Double.parseDouble(salary.getText());
        String id = comEmpId.getValue();
        String Name = lblName.getText();
        String date1 = String.valueOf(date.getValue());


        var dto = new SalaryDTO(amount, id, Name, date1);

        try {
            boolean isaddite = SalaryModel.addSalary(dto);
            if (isaddite) {
                new Alert(Alert.AlertType.CONFIRMATION, "Add Successful").show();
                clearField();
                loadAllSalary();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public boolean sendMain() throws MessagingException {

        String from = "nshanilka999@gmail.com"; // sender's email address
        String host = "localhost"; // replace with your email password

        Properties props = new Properties();
        props.setProperty("mail.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("nshanilka999@gmail.com", "wupawoazypqsfghh");
            }
        });

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(from));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(To));
        mimeMessage.setSubject(Subject);
        mimeMessage.setText(Msg);

        Transport.send(mimeMessage);
        return true;
    }

    public void run() {
        if (Msg != null && !Msg.isEmpty()) {
            try {
                sendMain();
                System.out.println("Email sent successfully.");
            } catch (MessagingException e) {
                throw new RuntimeException("Error sending email: " + e.getMessage(), e);
            }
        } else {
            System.out.println("Not sent. Empty message!");
        }
    }

    public void BackOnAction(ActionEvent event) {

    }

    private void loadEmployeeId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<AddEmployeeDTO> idList = AddEmployeeModel.getAllEmployee();

            for (AddEmployeeDTO dto : idList) {
                obList.add(dto.getEmployeeId());
            }

            comEmpId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void comEmpIdOnAction(ActionEvent event) {
        String id = (String) comEmpId.getValue();
        try {
            AddEmployeeDTO dto = AddEmployeeModel.searchEmployee(id);
            lblName.setText(dto.getEmployeeName());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllSalary() {
        var model = new SalaryModel();

        try {
            List<SalaryDTO> dtoList = model.getAllSalary();

            for (SalaryDTO dto : dtoList) {
                Button btn = new Button("Remove");
                //setRemoveBtnAction(btn, dto);
                obList.add(
                        new SalaryTm(
                                dto.getDate(),
                                dto.getEmployeeId(),
                                dto.getEmployeeName(),
                                dto.getSalary(),
                                btn
                        )
                );
            }
            salaryTm.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void sendEmailOnAction(ActionEvent event) {
        System.out.println("Start");
        Sending.setText("sending...");
        SalaryController mail = new SalaryController(); //creating an instance of Mail class
        mail.setMsg(txtMsg.getText());//email message
        mail.setTo(txtTo.getText()); //receiver's mail
        mail.setSubject(txtSubject.getText()); //email subject

        Thread thread = new Thread(mail);
        thread.start();

        System.out.println("end");
        Sending.setText("sended");

    }


    public static void sendMail(String recepient, int otp) throws MessagingException {
        try {
            System.out.println("Preparing to send email");
            Properties properties = new Properties();


            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            String myAccountEmail = "nshanilka999@gmail.com";
            String password = "wupawoazypqsfghh";

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(myAccountEmail, password);
                }
            });
            Message message = prepareMessage(session, myAccountEmail, recepient, otp);
            Transport.send(message);
            System.out.println("Email Send successfully");
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Connect Internet Connection !!").show();
        }
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, int otp) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Your OTP");
            message.setText("Your OTP is");
            return message;
        } catch (Exception e) {
//            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Connect Internet Connection !!").show();
        }
        return null;
    }
}



