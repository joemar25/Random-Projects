
/**
 * made by Joemar J. Cardiño
 */

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {

    final float DEDUCT = 600.0f;

    JTextField empName, empMon, empTue, empWed, empThu, empFri,
            hourlyRate, hoursAbsent, monthlySalary, monthlyAbsenses, monthlyTardiness,
            deduction, grossPay, netPay;
    JButton button;

    Main() {
        // frame
        JFrame f = new JFrame();

        // button - set bound to x axis, y axis, width, height of button
        button = new JButton("Compute");

        // labels
        JLabel blank = new JLabel("");
        JLabel empLabel = new JLabel("Enter Employee's Name: ", SwingConstants.RIGHT);
        JLabel monLabel = new JLabel("Monday: ", SwingConstants.RIGHT);
        JLabel tueLabel = new JLabel("Tuesday: ", SwingConstants.RIGHT);
        JLabel wedLabel = new JLabel("Wednesday: ", SwingConstants.RIGHT);
        JLabel thuLabel = new JLabel("Thursday: ", SwingConstants.RIGHT);
        JLabel friLabel = new JLabel("Friday: ", SwingConstants.RIGHT);
        JLabel hrLabel = new JLabel("Enter Employee's Hourly Rate: ", SwingConstants.RIGHT);
        JLabel haLabel = new JLabel("Enter Employee's Hours Absent: ", SwingConstants.RIGHT);
        JLabel bspm = new JLabel("Salary Per Month", SwingConstants.CENTER);
        JLabel tapm = new JLabel("Total Absense Per Month", SwingConstants.CENTER);
        JLabel ttpm = new JLabel("Total Tardiness Per Month", SwingConstants.CENTER);
        JLabel pgpd = new JLabel("SSS, PAGIBIG, and PHILHEALTH deduction", SwingConstants.CENTER);
        JLabel gp = new JLabel("Gross Pay", SwingConstants.CENTER);
        JLabel np = new JLabel("Net Pay", SwingConstants.CENTER);

        // text fields
        empName = new JTextField("");
        empMon = new JTextField("");
        empTue = new JTextField("");
        empWed = new JTextField("");
        empThu = new JTextField("");
        empFri = new JTextField("");
        hourlyRate = new JTextField("");
        hoursAbsent = new JTextField("");
        monthlySalary = new JTextField("");
        monthlySalary.setEditable(false);
        monthlyAbsenses = new JTextField("");
        monthlyAbsenses.setEditable(false);
        monthlyTardiness = new JTextField("");
        monthlyTardiness.setEditable(false);
        deduction = new JTextField("");
        deduction.setEditable(false);
        grossPay = new JTextField("");
        grossPay.setEditable(false);
        netPay = new JTextField("");
        netPay.setEditable(false);

        // adding objects to JFrame
        f.add(empLabel);
        f.add(empName);
        f.add(monLabel);
        f.add(empMon);
        f.add(tueLabel);
        f.add(empTue);
        f.add(wedLabel);
        f.add(empWed);
        f.add(thuLabel);
        f.add(empThu);
        f.add(friLabel);
        f.add(empFri);
        f.add(hrLabel);
        f.add(hourlyRate);
        f.add(haLabel);
        f.add(hoursAbsent);
        f.add(blank);
        f.add(button);
        f.add(blank);
        f.add(bspm);
        f.add(monthlySalary);
        f.add(tapm);
        f.add(monthlyAbsenses);
        f.add(ttpm);
        f.add(monthlyTardiness);
        f.add(pgpd);
        f.add(deduction);
        f.add(gp);
        f.add(grossPay);
        f.add(np);
        f.add(netPay);

        // click button
        button.addActionListener(this);
        // setting frame
        f.setSize(500, 500);
        // using grid layout 3 * 3 grid is created
        // with the horizontal gap 9 and vertical gap 9
        f.setLayout(new GridLayout(15, 14, 15, 15));
        // making the frame visible
        f.setVisible(true);
        // center alignment of frame
        f.setLocationRelativeTo(null);
        // exit on close
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        int m = 0, t = 0, w = 0, th = 0, f = 0, ha = 0;
        float hr = 0;
        if (!empMon.getText().isEmpty()) {
            m = Integer.parseInt(empMon.getText());
        }
        if (!empTue.getText().isEmpty()) {
            t = Integer.parseInt(empTue.getText());
        }
        if (!empWed.getText().isEmpty()) {
            w = Integer.parseInt(empWed.getText());
        }
        if (!empThu.getText().isEmpty()) {
            th = Integer.parseInt(empThu.getText());
        }
        if (!empFri.getText().isEmpty()) {
            f = Integer.parseInt(empFri.getText());
        }
        if (!empFri.getText().isEmpty()) {
            f = Integer.parseInt(empFri.getText());
        }
        if (!hourlyRate.getText().isEmpty()) {
            hr = Float.parseFloat(hourlyRate.getText());
        }
        if (!hoursAbsent.getText().isEmpty()) {
            ha = Integer.parseInt(hoursAbsent.getText());
        }

        int totalHours = m + t + w + th + f;
        float totalRate = totalHours * hr;
        float absense = ha * hr;

        // 4 means, 4 weeks in a month - to get monthly salary
        // no deduction, just calculate for the month
        float ms = (totalRate * 4);

        if (e.getSource() == button) {
            monthlySalary.setText(Float.toString(ms));
            monthlyAbsenses.setText(Integer.toString(ha));
            monthlyTardiness.setText(Integer.toString(0));
            deduction.setText(Float.toString(ms - DEDUCT));
            grossPay.setText(Float.toString(ms));
            netPay.setText(Float.toString(ms - (absense + DEDUCT)));
        }
    }

    public static void main(String args[]) {
        new Main();
    }
}