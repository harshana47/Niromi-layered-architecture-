package lk.ijse.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.impl.EmployeeBoImpl;
import lk.ijse.model.EmployeeDTO;
import lk.ijse.dao.custom.impl.EmployeeDAOImpl;

import java.sql.Connection;
import java.sql.SQLException;

public class EmployeeFormController {
    public TextField txtEmployeeId;
    public TextField txtName;
    public Button btnDelete;
    public Button btnSearch;
    public Button btnUpdate;
    public Button btnSave;
    public Button btnBack;
    public TextField txtPosition;
    public TextField txtDuty;
    public TextField txtEmail;

    @FXML
    private TextField txtDeptId;

    @FXML
    private TableView<EmployeeDTO> tblEmployees;

    @FXML
    private TableColumn<EmployeeDTO, String> colEmail;

    @FXML
    private TableColumn<EmployeeDTO, String> colEmployeeId;

    @FXML
    private TableColumn<EmployeeDTO, String> colName;

    @FXML
    private TableColumn<EmployeeDTO, String> colDeptId;

    @FXML
    private TableColumn<EmployeeDTO, String> colPosition;

    @FXML
    private TableColumn<EmployeeDTO, String> colDuty;

    private AnchorPane node;

    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);


    private ObservableList<EmployeeDTO> employeeDTOList = FXCollections.observableArrayList();

    public EmployeeFormController() {
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtEmployeeId.getText();
        String name = txtName.getText();
        String depId = txtDeptId.getText();
        String position = txtPosition.getText();
        String duty = txtDuty.getText();
        String email = txtEmail.getText();

        if (isValid()) {
            EmployeeDTO employeeDTO = new EmployeeDTO(id, name, depId, position, duty, email);

            boolean isSaved = employeeBO.save(employeeDTO);
            if (isSaved) {
                employeeBO.increaseStaffCount(depId);
                employeeDTOList.add(employeeDTO);
                new Alert(Alert.AlertType.CONFIRMATION, "Employee added successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to add employee!!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtEmployeeId.getText();
        String name = txtName.getText();
        String depId = txtDeptId.getText();
        String position = txtPosition.getText();
        String duty = txtDuty.getText();
        String email = txtEmail.getText();

        EmployeeDTO employeeDTO = new EmployeeDTO(id, name, depId, position, duty, email);

        boolean isUpdated = employeeBO.update(employeeDTO);
        if (isUpdated) {
            initialize();
            new Alert(Alert.AlertType.CONFIRMATION, "Employee updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update employee!").show();
        }
    }

    @FXML
    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtEmployeeId.getText();
        EmployeeDTO employeeDTO = employeeBO.search(id);
        if (employeeDTO != null) {
            txtName.setText(employeeDTO.getName());
            txtDeptId.setText(employeeDTO.getDepId());
            txtPosition.setText(employeeDTO.getPosition());
            txtDuty.setText(employeeDTO.getDuty());
            txtEmail.setText(employeeDTO.getEmail());
            new Alert(Alert.AlertType.INFORMATION, "Employee found!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Employee not found!").show();
        }
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        EmployeeDTO selectedEmployeeDTO = tblEmployees.getSelectionModel().getSelectedItem();
        if (selectedEmployeeDTO != null) {
            boolean isDeleted = employeeBO.delete(selectedEmployeeDTO.getEmployeeId());
            if (isDeleted) {
                employeeBO.decreaseStaffCount(selectedEmployeeDTO.getEmployeeId());
                employeeDTOList.remove(selectedEmployeeDTO);
                new Alert(Alert.AlertType.CONFIRMATION, "Employee deleted successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete employee!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please select an employee to delete!").show();
        }
    }

    @FXML
    public void initialize() throws ClassNotFoundException {
        try {

            colEmployeeId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmployeeId()));
            colName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
            colDeptId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDepId()));
            colPosition.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPosition()));
            colDuty.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDuty()));
            colEmail.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEmail()));
            tblEmployees.setItems(employeeDTOList) ;

            employeeDTOList.addAll(employeeBO.getAllEmployees());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Error initializing: " + e.getMessage()).show();
        }
    }

    public void txtEmployeeIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.ID,txtEmployeeId);
    }

    public void txtEmployeeNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtName);
    }

    public void txtEmployeePositionOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtPosition);
    }

    public void txtEmployeeDutyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtDuty);
    }

    public void txtDepartmentIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.THREEID,txtDeptId);
    }
    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.Util.TextField.ID,txtEmployeeId));
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtName));
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtPosition));
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtDuty));
        if (!Regex.setTextColor(lk.ijse.Util.TextField.THREEID,txtDeptId));
        return true;
    }
}
