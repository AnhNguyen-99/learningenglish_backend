package com.learningenglish.service.impl;

import com.learningenglish.dto.UserExport;
import com.learningenglish.entiy.Role;
import com.learningenglish.entiy.RoleName;
import com.learningenglish.entiy.User;
import com.learningenglish.repository.UserRepository;
import com.learningenglish.service.RoleService;
import com.learningenglish.service.UserService;
import com.learningenglish.util.FileUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> findUserByPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User create(User user) {
        User newUser = new User();
        if (user.getId() == null) {
            // Set role in user
            Set<Role> reqRole = user.getRoles();
            Set<Role> roles = new HashSet<>();
            if (reqRole == null) {
                Role userRole = roleService.findByName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                roles.add(userRole);
            } else {
                reqRole.forEach(role -> {
                    switch (role.getName()) {
                        case ROLE_ADMIN: {
                            addRoles(RoleName.ROLE_ADMIN, roles);
                        }
                        default: {
                            addRoles(RoleName.ROLE_USER, roles);
                        }
                    }
                });
            }
            newUser.setRoles(roles);
            if (user.getPassword() == null)
                newUser.setPassword(passwordEncoder.encode("1"));
            else
                newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setEmail(user.getEmail());
            newUser.setCreateDate(new Date());
            newUser.setStatus(true);
        } else {
            User oldUser = userRepository.findById(user.getId()).get();
            newUser.setId(user.getId());
            newUser.setUsername(oldUser.getUsername());
            // Email
            if (user.getEmail() == null)
                newUser.setEmail(oldUser.getEmail());
            else
                newUser.setEmail(user.getEmail());
            // Password
            if (user.getPassword() == null)
                newUser.setPassword(oldUser.getPassword());
            else
                newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            // Status
            if (user.getStatus() == null)
                newUser.setStatus(oldUser.getStatus());
            else
                newUser.setStatus(user.getStatus());
            if (user.getRoles() == null)
                newUser.setRoles(oldUser.getRoles());
            // CreateDate
            newUser.setCreateDate(oldUser.getCreateDate());
            // Login Date
            newUser.setLastestLoginDate(oldUser.getLastestLoginDate());
        }
        return userRepository.save(newUser);
    }


    @Override
    public User exitByEmail(String email) {
        return userRepository.exitsByEmail(email);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User importList(MultipartFile file) throws Exception {
        User user = new User();
        String name = file.getOriginalFilename();
        int validateFile = FileUtil.validateAttachFile(file, name);
        if (validateFile != 0) {
            return null;
        } else {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet workSheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = workSheet.rowIterator();
            List<Row> lstRow = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                lstRow.add(row);
            }
            // Kiểm tra file rỗng
            if (lstRow.size() <= 1) {
                return null;
            } else {
                List<User> listUser = new ArrayList<>();
                Cell headerCellError = lstRow.get(1).createCell(8);
                for (int i = 2; i < lstRow.size(); i++) {
                    Row row = lstRow.get(i);
                    XSSFWorkbook wb = (XSSFWorkbook) row.createCell(8).getRow().getSheet().getWorkbook();
                    XSSFCell xssfCell = (XSSFCell) row.createCell(8);
                    CellStyle cellStyle = wb.createCellStyle();
                    XSSFFont font = wb.createFont();
                    font.setBold(true);
                    font.setColor(IndexedColors.RED.getIndex());
                    cellStyle.setFont(font);
                    cellStyle.setWrapText(true);
                    cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
                    xssfCell.setCellStyle(cellStyle);
                    DataFormatter format = new DataFormatter();
                    if (row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null
                            || row.getCell(3) == null || row.getCell(4) == null || row.getCell(5) == null
                            || row.getCell(6) == null || row.getCell(7) == null) {
                        xssfCell.setCellValue("Không có dữ liệu");
                    }
                }
            }
        }
        return user;
    }

    @Override
    public Boolean deleteById(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<UserExport> findAllExport() {
        List<User> listUser = userRepository.findAll();
        List<UserExport> listUserExport = new ArrayList<>();
        listUser.forEach(user -> {
            listUserExport.add(new UserExport(user.getUsername(), user.getEmail()));
        });
        return listUserExport;
    }

    @Override
    public Page<User> findByUserNameOrEmail(String username, String email, Pageable pageable) {
        return userRepository.findAllByUsernameContainsOrEmailContains(username, email, pageable);
    }

    @Override
    public User changeStatus(User user) {
        if (user.getStatus() == true)
            user.setStatus(false);
        else
            user.setStatus(true);
        return create(user);
    }

    public void addRoles(RoleName roleName, Set<Role> roles) {
        Role userRole = roleService.findByName(roleName).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
        roles.add(userRole);
    }
}
