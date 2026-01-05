package MainFrame;

import Controller.GuestController;
import Controller.RegisterController;
import Controller.ReportsController;
import Model.GuestModel;
import Model.RegisterModel;
import Model.ReportsModel;
import View.GuestView;
import View.RegisterView;
import View.ReportsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    private JPanel contentArea;

    private JButton btnRegister;
    private JButton btnBooking;
    private JButton btnRooms;
    private  JButton btnCheckOut;
    private JButton btnGuest;
    private JButton btnReports;

    private MainFrame() {
        setTitle("Hotel Plaza Workspace");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);


        BackgroundPanel bgPanel = new BackgroundPanel("/baxkground2.jpg");
        bgPanel.setLayout(new BorderLayout());
        setContentPane(bgPanel);


        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(240, 800));

        sidebar.setBackground(new Color(20, 33, 61, 200));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));


        sidebar.add(Box.createRigidArea(new Dimension(0, 50)));


        btnRegister = createSidebarBtn("Register");
        btnBooking = createSidebarBtn("Room Booking");
        btnRooms = createSidebarBtn("Rooms");
        btnCheckOut = createSidebarBtn("Checkout");
        btnGuest = createSidebarBtn("Guests");
        btnReports = createSidebarBtn("Reports");



        sidebar.add(btnRegister);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(btnBooking);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(btnRooms);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(btnCheckOut);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(btnGuest);
        sidebar.add(Box.createRigidArea(new Dimension(0, 30)));

        sidebar.add(btnReports);

        add(sidebar, BorderLayout.WEST);



        contentArea = new JPanel(new BorderLayout());
        contentArea.setOpaque(false);
        add(contentArea, BorderLayout.CENTER);

        this.addGuestsListener (e -> {

            GuestModel model = new GuestModel();
            GuestView view = new GuestView();
            GuestController controller=new GuestController(view, model);


            switchPage(view);
        });

        this.addRegisterListener (e -> {

            RegisterModel model = new RegisterModel();
            RegisterView view = new RegisterView();
            RegisterController controller=new RegisterController(view, model);


            switchPage(view);
        });
        /*this.addBookingListener (e -> {

            BookingModel model = new BookingModel();
            BookingView view = new BookingView();
            BookingController controller=new BookingController(view, model);


            switchPage(view);
        });
        this.addRoomsListener (e -> {

            RoomsModel model = new RoomsModel();
            RoomsView view = new RoomsView();
            RoomsController controller=new RoomsController(view, model);


            switchPage(view);
        });
        this.addCheckOutlListener (e -> {

            CheckOutModel model = new  CheckOutModel();
            CheckOutView view = new CheckOutView ();
            CheckOutController controller=new CheckOutController(view, model);


            switchPage(view);
        }):*/
        this.addReportsListener (e -> {

            ReportsModel model = new ReportsModel();
            ReportsView view = new ReportsView();
            ReportsController controller=new ReportsController(view, model);


            switchPage(view);
        });
    }


    public static MainFrame getInstance() {
        if (instance == null) instance = new MainFrame();
        return instance;
    }

    public void switchPage(JPanel newPage) {
        contentArea.removeAll();


        newPage.setOpaque(false);

        contentArea.add(newPage, BorderLayout.CENTER);
        contentArea.revalidate();
        contentArea.repaint();
    }

    private JButton createSidebarBtn(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(220, 50));
        btn.setBackground(new Color(40, 60, 100));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        return btn;
    }

    class BackgroundPanel extends JPanel {
        private Image img;
        public BackgroundPanel(String path) {
            try {
                img = new ImageIcon(getClass().getResource(path)).getImage();
            } catch (Exception e) {
                System.out.println("Background image not found: " + path);
            }
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }


    public void addRegisterListener(ActionListener listener)
    {
        btnRegister.addActionListener(listener);
    }
    public void addBookingListener(ActionListener listener)
    {
        btnBooking.addActionListener(listener);
    }
    public void addRoomsListener(ActionListener listener)
    {
        btnRooms.addActionListener(listener);
    }
    public void addCheckOutlListener(ActionListener listener)
    {
        btnCheckOut.addActionListener(listener);
    }
    public void addGuestsListener(ActionListener listener)
    {
        btnGuest.addActionListener(listener);
    }
    public void addReportsListener(ActionListener listener)
    {
        btnReports.addActionListener(listener);
    }



}



