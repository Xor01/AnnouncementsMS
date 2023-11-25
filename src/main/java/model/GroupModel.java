package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupModel {
    private String groupName;
    private int groupId;
    private int userId;
    private boolean isAdmin;
    private Connection con;

    private GroupModel(int userId, int groupId, String groupName, boolean isAdmin) {

        this.userId = userId;
        this.groupId = groupId;
        this.groupName = groupName;
        this.isAdmin = isAdmin;
    }

    public GroupModel(){try {
        this.con = ConnectionManager.getConnection();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }}

    public ArrayList<GroupModel> getGroups(int userId) {
        try {
            String query = "SELECT agroups.*, groupmembers.group_id, groupmembers.isAdmin " +
                    "FROM agroups " +
                    "JOIN groupmembers ON agroups.id = groupmembers.group_id " +
                    "WHERE groupmembers.member_id = ?";

            PreparedStatement selectGroupsPreparedStatement = con.prepareStatement(query);
            selectGroupsPreparedStatement.setInt(1, userId);
            ResultSet result = selectGroupsPreparedStatement.executeQuery();
            ArrayList<GroupModel> listOfGroups = new ArrayList<>();
            while (result.next()) {
                String groupName = result.getString("gName");
                int groupId = result.getInt("group_id");
                boolean isAdmin = result.getBoolean("isAdmin");
                GroupModel groupModel = new GroupModel(userId, groupId, groupName, isAdmin);
                listOfGroups.add(groupModel);
            }
            return listOfGroups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}