import java.util.*;
import java.util.List;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

    public class Student {
        String name;
        int rollNo;
        String grade;
        Student(String name, int rollNo, String grade) {
            this.name = name;
            this.rollNo = rollNo;
            this.grade = grade;
        }
        @Override
        public String toString() {
            return "Name: " + name + ", Roll No: " + rollNo + ", Grade: " + grade;
        }
        static void saveStudentsToFile(List<Student> students, String filename) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {  // 'false' for overwriting the file
                for (Student student : students) {
                    writer.write(student.name + "," + student.rollNo + "," + student.grade);
                    writer.newLine(); // Add a new line for each student
                }
            } catch (IOException ex) {
                System.out.println("An error occurred while trying to save students: " + ex.getMessage());
            }
        }

        public static void main(String[] args) {
            List<Student> students = new ArrayList<>();
            String filename = "/Users/ayushmohite/Downloads/students.csv";
            JFrame frame = new JFrame("Student Management System");
            students.clear();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    if (values.length == 3) { // Ensure there are three values in each line
                        try {
                            students.add(new Student(values[0], Integer.parseInt(values[1]), values[2]));
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing 'rollNo' as an integer: " + e.getMessage());
                        }
                    } else {
                        System.err.println("Invalid data format in line: " + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            frame.setLayout(new FlowLayout());

            JTextField nameField = new JTextField(10);
            JTextField rollNoField = new JTextField(10);
            JTextField gradeField = new JTextField(10);

            JButton addButton = new JButton("Add Student");
            JButton removeButton = new JButton("remove Student");
            removeButton.setBackground(Color.yellow);
            removeButton.setForeground(Color.black);
            JButton searchButton = new JButton("Search Student");
            searchButton.setBackground(Color.BLUE);
            searchButton.setForeground(Color.black);
            JButton displayButton = new JButton("Display all");
            displayButton.setBackground(Color.RED);
            displayButton.setForeground(Color.black);

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JTextField rollNoInput = new JTextField(10);

                    JPanel inputPanel = new JPanel();
                    inputPanel.setLayout(new GridLayout(1, 2));
                    inputPanel.add(new JLabel("Roll No:"));
                    inputPanel.add(rollNoInput);

                    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Enter Roll No to Remove", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        String rollNoText = rollNoInput.getText().trim();
                        if (!rollNoText.isEmpty()) {
                            int rollNo = Integer.parseInt(rollNoText);
                            students.removeIf(student -> student.rollNo == rollNo);
                            saveStudentsToFile(students, filename);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Roll No.");
                        }
                    }
                }
            });

            searchButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JTextField rollNoInput = new JTextField(10);

                    JPanel inputPanel = new JPanel();
                    inputPanel.setLayout(new GridLayout(1, 2));
                    inputPanel.add(new JLabel("Roll No:"));
                    inputPanel.add(rollNoInput);

                    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Enter Roll No to Search", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        String rollNoText = rollNoInput.getText().trim();
                        if (!rollNoText.isEmpty()) {
                            int rollNoToSearch = Integer.parseInt(rollNoText);
                            boolean studentFound = false;
                            for (Student student : students) {
                                if (student.rollNo == rollNoToSearch) {
                                    JOptionPane.showMessageDialog(null, student);
                                    studentFound = true;
                                    break;
                                }
                            }
                            if (!studentFound) {
                                JOptionPane.showMessageDialog(null, "No Student found.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter a valid Roll No.");
                        }
                    }
                }
            });
            displayButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    for (Student student : students) {
                        JOptionPane.showMessageDialog(null, student);
                    }
                }
            });
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    JTextField nameInput = new JTextField(10);
                    JTextField rollNoInput = new JTextField(10);
                    JTextField gradeInput = new JTextField(10);

                    JPanel inputPanel = new JPanel();
                    inputPanel.setLayout(new GridLayout(3, 2));
                    inputPanel.add(new JLabel("Name:"));
                    inputPanel.add(nameInput);
                    inputPanel.add(new JLabel("Roll No:"));
                    inputPanel.add(rollNoInput);
                    inputPanel.add(new JLabel("Grade:"));
                    inputPanel.add(gradeInput);

                    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Enter Student Information", JOptionPane.OK_CANCEL_OPTION);

                    if (result == JOptionPane.OK_OPTION) {
                        String name = nameInput.getText();
                        String rollNoText = rollNoInput.getText();
                        String grade = gradeInput.getText();

                        if (!name.isEmpty() && !rollNoText.isEmpty() && !grade.isEmpty()) {
                            int rollNo = Integer.parseInt(rollNoText);
                            Student newStudent = new Student(name, rollNo, grade);
                            students.add(newStudent);
                            saveStudentsToFile(students, filename);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                        }
                    }
                }
            });

            frame.add(addButton);
            frame.add(searchButton);
            frame.add(displayButton);
            frame.add(removeButton);
            frame.setVisible(true);

            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
    }

