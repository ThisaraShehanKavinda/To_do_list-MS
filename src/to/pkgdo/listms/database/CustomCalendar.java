/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package to.pkgdo.listms.database;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class CustomCalendar extends JPanel {
    private int currentYear;
    private int currentMonth;
    private JLabel monthLabel;
    private JButton prevButton;
    private JButton nextButton;
    private JPanel daysPanel;
    private LocalDate selectedDate;

    public CustomCalendar() {
        selectedDate = LocalDate.now();
        currentYear = selectedDate.getYear();
        currentMonth = selectedDate.getMonthValue();

        initComponents();
        updateCalendar();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Header with previous and next buttons
        JPanel headerPanel = new JPanel(new BorderLayout());
        prevButton = new JButton("<<");
        prevButton.addActionListener(e -> previousMonth());
        nextButton = new JButton(">>");
        nextButton.addActionListener(e -> nextMonth());
        monthLabel = new JLabel("", JLabel.CENTER);
        headerPanel.add(prevButton, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextButton, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Panel to display the days of the month
        daysPanel = new JPanel(new GridLayout(0, 7));
        add(daysPanel, BorderLayout.CENTER);
    }

    private void previousMonth() {
        currentMonth--;
        if (currentMonth <= 0) {
            currentMonth = 12;
            currentYear--;
        }
        updateCalendar();
    }

    private void nextMonth() {
        currentMonth++;
        if (currentMonth > 12) {
            currentMonth = 1;
            currentYear++;
        }
        updateCalendar();
    }

    private void updateCalendar() {
        monthLabel.setText(getMonthName(currentMonth) + " " + currentYear);
        daysPanel.removeAll();

        LocalDate firstDayOfMonth = LocalDate.of(currentYear, currentMonth, 1);
        int startDay = firstDayOfMonth.getDayOfWeek().getValue();
        int daysInMonth = firstDayOfMonth.lengthOfMonth();

        for (int i = 1; i <= daysInMonth; i++) {
            JButton dayButton = new JButton(String.valueOf(i));
            dayButton.setMargin(new Insets(0, 0, 0, 0));
            dayButton.addActionListener(e -> handleDateClick(dayButton.getText()));
            if (currentYear == selectedDate.getYear() && currentMonth == selectedDate.getMonthValue() && i == selectedDate.getDayOfMonth()) {
                dayButton.setForeground(Color.RED);
            }
            daysPanel.add(dayButton);
        }

        // Fill empty cells in the first row if necessary
        for (int i = 1; i < startDay; i++) {
            daysPanel.add(new JLabel());
        }

        // Repaint the panel to show the changes
        daysPanel.revalidate();
        daysPanel.repaint();
    }

    private void handleDateClick(String day) {
        int selectedDay = Integer.parseInt(day);
        selectedDate = LocalDate.of(currentYear, currentMonth, selectedDay);
        updateCalendar();
        // Add any additional logic for date selection as per your requirements
    }

    private String getMonthName(int month) {
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return monthNames[month - 1];
    }
}
