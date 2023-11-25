package controller;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import model.GroupModel;

import javax.swing.*;
import java.awt.*;

public class GroupController {
    private GroupModel groupModel;

    public GroupController(GroupModel groupModel){
        this.groupModel = groupModel;
    }

    public void addGroupToView(JTabbedPane groupBar){
        JTextPane announcementsPanel = new JTextPane();
        announcementsPanel.setFocusable(false);
        JPanel groupDetails = new JPanel(new BorderLayout());
        groupDetails.add(new JScrollPane(announcementsPanel), BorderLayout.CENTER);
        JButton addUser = new JButton(new FlatSVGIcon("addUser.svg"));
        addUser.setToolTipText("Add users to " + groupModel.getGroupName());
        /*  creating the three panels to have right and left panels both added to the main panel*/
        JPanel mainTopPanel = new JPanel(new BorderLayout());
        JPanel leftTopPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel rightTopPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        // adding left and right panels to the main panel
        mainTopPanel.add(leftTopPanel, BorderLayout.WEST);
        mainTopPanel.add(rightTopPanel, BorderLayout.EAST);

        JButton updateBtn = new JButton(new FlatSVGIcon("refresh.svg"));
        updateBtn.setToolTipText("Refresh");
        leftTopPanel.add(updateBtn);
//        updateBtn.addActionListener(e -> {
//            groupsTabs.removeAll();
//            addGroups();
//        });
        leftTopPanel.add(addUser);
        if (1==2){
            addUser.setEnabled(false);
            addUser.setToolTipText("Only admins can add users");
        }
        groupDetails.add(mainTopPanel, BorderLayout.NORTH);
        announcementsPanel.setEditable(false);
        groupBar.addTab(groupModel.getGroupName(), groupDetails);
        int group_id = groupModel.getGroupId();
//        group_ids.add(group_id);
        groupDetails.putClientProperty("group_id", group_id);
        groupDetails.putClientProperty("isGroupAdmin", "isGroupAdmin");
        // this will create the group member list
//        if (isGroupAdmin){
//            ListUsersOfAGroup listUsersOfAGroup = new ListUsersOfAGroup(con, group_id, this);
//            listUsersOfAGroup.doWork();
//            JButton listMembers = new JButton(new FlatSVGIcon("group.svg"));
//            listMembers.setToolTipText("List the members of group");
//            listMembers.addActionListener(e -> listUsersOfAGroup.showPopup());
//            rightTopPanel.add(listMembers);
//        }
//        addUser.addActionListener(new AddUserToGroup(this, this.con, group_id));
//        loadMessages(group_id, announcementsPanel);
    }
}
