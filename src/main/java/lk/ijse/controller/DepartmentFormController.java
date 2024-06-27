package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyIntegerWrapper;
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
import lk.ijse.Util.Regex;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.DepartmentBO;
import lk.ijse.bo.custom.EmployeeBO;
import lk.ijse.bo.impl.DepartmentBoImpl;
import lk.ijse.bo.impl.EmployeeBoImpl;
import lk.ijse.model.DepartmentDTO;
import lk.ijse.dao.custom.impl.DepartmentDAOImpl;
import lk.ijse.dao.custom.impl.EmployeeDAOImpl;

import java.sql.SQLException;

public class DepartmentFormController {
    public JFXButton btnBack;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<DepartmentDTO, String> colDepartmentId;

    @FXML
    private TableColumn<DepartmentDTO, String> colName;

    @FXML
    private TableColumn<DepartmentDTO, Integer> colStaffCount;

    @FXML
    private TableView<DepartmentDTO> tblDepartments;

    @FXML
    private TextField txtDeptId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtStaffCount;

    private ObservableList<DepartmentDTO> departmentDTOList = FXCollections.observableArrayList();

    EmployeeBO employeeBo = (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    DepartmentBO departmentBO = (DepartmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DEPARTMENT);

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String depId = txtDeptId.getText();
        String name = txtName.getText();
        int staffCount = Integer.parseInt(txtStaffCount.getText());

        int employeeCount = employeeBo.getEmployeeCount();
        if (staffCount <= employeeCount) {
            DepartmentDTO departmentDTO = new DepartmentDTO(depId, name, staffCount);
            if (isValied()) {
                boolean isAdded = departmentBO.save(departmentDTO);
                if (isAdded) {
                    departmentDTOList.add(departmentDTO);
                    new Alert(Alert.AlertType.CONFIRMATION, "Department added successfully!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to add department!").show();
                }
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Staff count cannot exceed the number of employees!").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String depId = txtDeptId.getText();
        String name = txtName.getText();
        int staffCount = Integer.parseInt(txtStaffCount.getText());

        int employeeCount = employeeBo.getEmployeeCount();
        if (staffCount <= employeeCount) {
            DepartmentDTO departmentDTO = new DepartmentDTO(depId, name, staffCount);
            boolean isUpdated = departmentBO.update(departmentDTO);
            if (isUpdated) {
                initialize();
                new Alert(Alert.AlertType.CONFIRMATION, "Department updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update department!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Staff count cannot exceed the number of employees!").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DepartmentDTO selectedDepartmentDTO = tblDepartments.getSelectionModel().getSelectedItem();
        if (selectedDepartmentDTO != null) {
            boolean isDeleted = departmentBO.delete(selectedDepartmentDTO.getDepId());
            if (isDeleted) {
                departmentDTOList.remove(selectedDepartmentDTO);
                new Alert(Alert.AlertType.CONFIRMATION, "Department deleted successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete department!").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a department to delete!").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String depId = txtDeptId.getText();
        DepartmentDTO departmentDTO = departmentBO.search(depId);
        if (departmentDTO != null) {
            txtName.setText(departmentDTO.getName());
            txtStaffCount.setText(String.valueOf(departmentDTO.getStaffCount()));
        } else {
            new Alert(Alert.AlertType.ERROR, "Department not found!").show();
            clearFields();
        }
    }

    private void clearFields() {
        txtDeptId.clear();
        txtName.clear();
        txtStaffCount.clear();
    }

    @FXML
    public void initialize() throws SQLException, ClassNotFoundException {

        colDepartmentId.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getDepId()));
        colName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
        colStaffCount.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getStaffCount()).asObject());

        tblDepartments.setItems(departmentDTOList);

        departmentBO.load(departmentDTOList);
    }

    public void txtDepartmentIDOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.THREEID,txtDeptId);
    }

    public void txtDepartmentNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtName);
    }

    public void txtDepartmentStaffOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.Util.TextField.COUNT,txtStaffCount);
    }
    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.Util.TextField.THREEID,txtDeptId)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.NAME,txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.Util.TextField.COUNT,txtStaffCount)) return false;
        return true;
    }
}
