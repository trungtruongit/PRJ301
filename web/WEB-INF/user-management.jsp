<%--
    Document   : user-management
    Created on : Dec 22, 2023, 10:47:45 PM
    Author     : Oscar
--%>

<%@page import="java.util.List"%>
<%@page import="oscar.utils.Constants"%>
<%@page import="oscar.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN USER");
            if (loginUser == null) {
                response.sendRedirect(Constants.LOGIN_PAGE);
                return;
            }

            String searchValue = (String) request.getParameter("searchValue");
            if (searchValue == null) {
                searchValue = "";
            }

        %>
        <div class="header">
            <h1>Welcome, <%= loginUser.getFullName()%></h1>
            <form action="MainController">
                <button type="submit" name="action" value="Logout">
                    Logout
                </button>
            </form>
        </div>
        <br>
        <div class="data-table">
            <form action="MainController">
                <label>Full Name</label>
                <input type="text" name="searchValue" value="">

                <button type="submit" name="action" value="SearchUser">
                    Search
                </button>
            </form> <br>
            <%
                List<UserDTO> users = (List<UserDTO>) request.getAttribute("RESULT");
                if (users != null) {
                    if (!users.isEmpty()) {
            %>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Email</th>
                        <th>Full Name</th>
                        <th>Address</th>
                        <th>Country</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>

                <tbody>
                    <%
                        int count = 1;
                        for (UserDTO user : users) {
                    %>
                <form action="MainController">
                    <tr>
                        <td><%= count++%></td>
                        
                        <td>
                            <input type="email" name="email"
                                   value="<%= user.getEmail()%>" readonly="">
                        </td>
                        
                        <td>
                            <input type="text" name="fullName"
                                   value="<%= user.getFullName()%>" readonly="">
                        </td>
                        
                        <td>
                            <input type="text" name="address"
                                   value="<%= user.getAddress()%>" readonly="">
                        </td>
                        
                        <td>
                            <input type="text" name="country"
                                   value="<%= user.getCountry()%>" readonly="">
                        </td>
                        
                        <td>
                            <input type="hidden" name="searchValue"
                                   value="<%= searchValue %>">
                        </td>
                        
                        <td>
                            <input type="hidden" name="email"
                                   value="<%= user.getEmail() %>" readonly="">
                            <button type="submit" name="action" value="DeleteUser">
                                Delete
                            </button>
                        </td>
                        
                        <td>
                            <input type="hidden" name="searchValue"
                                   value="<%= searchValue %>">
                        </td>
                        
                        <td>
                            <input type="hidden" name="email"
                                   value="<%= user.getEmail() %>" readonly="">
                            <button type="submit" name="action" value="UpdateUser">
                                Update
                            </button>
                        </td>
                    </tr>
                </form>
                <%
                    }
                %>

                </tbody>
            </table>
            <%
                    }
                }
                String message = (String) request.getAttribute("MSG");
                if (message == null) {
                    message = "";
                }
            %>
            <h2 style="color: red"><%= message%></h2>
        </div>
    </body>
</html>
