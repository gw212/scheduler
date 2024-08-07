- Using simple @Valid annotation for validating inputs
- No user authentication, would implement in this case Spring Security and use whatever existing authentication system exists to plug into this application.  

    - The isValid methods on the controllers would be moved into the security configuration/implementation class
- Error handling is pretty straight forward with simple try/catch blocks.  Ideally would try and cover more known edge cases with more guided error handling.  These instead are all going to be wrapped under a 500 status code instead of a more helpful coding system i.e. 1432 if provider does not exist, 1841 if an invalid date was given
- Documentation for the classes is pretty basic.  Examples and better clarification on valid values and what the data represents would be done if given more time
- Using JPARepository which isn't the most efficient for bulk operations but speeds development time.  Would use a framework like JdbcTemplate which is more effective for batch inserts/updates
- No SMTP server is set up here to send emails out so a stub method is called instead
- Confirmation API call sends a redirect, this is assuming the user is clicking on it from their email or somewhere similar that would take them to a UI
- scheduler is using simple Spring scheduled task. Would prefer to use a more centralized scheduler or a more complex framework that allows for better management and error handling
- Made the decision to not use "soft" deletes for this
- Ran into some issues with my local environment for the test containers, given more time would have liked to add some integration testing here the test schema used as well as some more unit tests for the other components.