# social-network

Mini social network project
using JSP / Servlet
Netbean IDE 


This project contains the following functions:
- Function 1: Register a new account
o A new account is registration following information such as email, name, password, role, status (email as
ID).
o The default role of new account is member.
o The default status of new account is New.
o Password  encrypted using SHA-256 before store in database.

- Function 2: Login
o The actor enters ID and password, the function checks if the ID with the password is in the available
account list, then grant the access permission. If not, a message would appear no notify that account is
not found.

- Function 3: Search article
o List the available article in the system and sort by date: tittle, description, image, date.
o Each page has a maximum of 20 records displayed. Paging is required to use.
o User inputs the text that they want to find and click the Search button or the Enter key. A list article
contains the search keyword (search by content).
o Login is required.

- Function 4: Display Article Details
o Users click on each post to see details such as comments, number of likes, and dislikes.
o Login is required.

- Function 5: Post the article
o In order to post the article to the social network, an authentication is required.
o If the user has not authenticated, the system redirects to the login page.
o Members can post their articles on the social network.
o The default status of the article is Active. The status will be changed to Delete if the user or Admin deletes
that post.
o Only member can use this function.

- Function 6: Post the comment
o For each article, authenticated user can post the comments.
o In order to post the comment, authentication is required.
o If the user has not authenticated, the system redirects to the login page.
o Members can post their comment on the article.
o Only member can use this function

- Function 7: Make emotions actions
o For each article, user shares their emotions about the post by click the icon like or dislike.
o An authentication is required.
o If the user has not authenticated, the system redirects to the login page.
o User can change their emotion from like to dislike or dislike to like.
o You must show the number of like and dislike of the post.
o Only member can use this function.

- Function 8: Show Notification:
o Show all user's interactions on your posts: userID, date, user's comment, user's emotion.
o The user clicks on each notification, the system will display the corresponding post and the interaction
details (comments, emotions)
o Login is required.

- Function 9: Delete the Article:
o User can delete his post or his comments.
o This action requires to show a confirmation message before deletion.

- Function 8: Verify registered user using email:
o When registering a new account, the system will send a confirmation code to the email address that is
registered with third party as Google, Yahoo, etc.
o After entering the verification code, the status of the account will change to Active.
o Only member can use this function.
