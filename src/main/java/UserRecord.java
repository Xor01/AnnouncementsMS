public record UserRecord(String username, String firstName, String lastName, String password, String email) {
    public String password(){
        PasswordHandler ps = new PasswordHandler(password);
        return ps.getHashedPassword();
    }
}