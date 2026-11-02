BARANGAY RECORDS MANAGEMENT SYSTEM
Apache NetBeans 31 + Java Swing + MySQL Workbench

FEATURES
- Add resident
- View all residents in a JTable
- Update selected resident
- Delete selected resident
- Search by resident ID, first name, or last name
- Clear input fields

REQUIREMENTS
- JDK 17 or newer
- Apache NetBeans 31
- MySQL Server
- MySQL Workbench
- Internet connection on the first Maven build (to download MySQL Connector/J)

DATABASE SETUP
1. Open MySQL Workbench and connect to your local MySQL server.
2. Open database.sql from this project.
3. Execute the whole SQL script (lightning bolt).
4. Confirm that the barangay_records schema and residents table were created.

NETBEANS SETUP
1. Extract the ZIP file.
2. Open NetBeans.
3. Choose File > Open Project and select the extracted BarangayRecordsManagementSystem folder.
4. Wait for Maven to download MySQL Connector/J.
5. Open DB.java.
6. Set DB_USER and DB_PASSWORD to your own MySQL account credentials.
   Do not use the sample password from another project.
7. Right-click the project and choose Clean and Build.
8. Run the project. The resident window should open.

IMPORTANT DATABASE CONNECTION
DB.java connects to:
jdbc:mysql://localhost:3306/barangay_records

Default username is root. Change the password constant in DB.java to your own
MySQL root password. If your MySQL server uses a different port, change 3306
in DB.java.

EDITING THE DESIGN (NetBeans GUI Builder)
- Open ResidentView.java and click the Design tab (top of the editor).
- The window is a visual JFrame Form (ResidentView.form): drag, resize and
  restyle components from the Palette and Properties panel.
- Do not edit the grey "Generated Code" block by hand; NetBeans rewrites it.
- Keep the component variable names (btnAdd, txtFirstName, tblResidents, ...)
  because ResidentController uses them.

HOW TO USE
- Enter the resident details and click Add Resident.
- Click a row in the table to load its details into the fields.
- Change the details and click Update Resident.
- Select a row and click Delete Resident to remove it.
- Enter a name or ID in the search box and click Search.
- Click Show All to reload all records.
- Click Clear to empty the form.

NOTE
This is a classroom starter project. For real barangay use, add login roles,
access controls, regular backups, audit logs, and privacy safeguards. Store
only information that the barangay is authorized to collect.
