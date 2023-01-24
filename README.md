






SOFTWARE PROJECT

ANALYSIS REPORT

FOR

THE NOTEBOOK

PROJECT
















# Contents
[**1.**	**Introduction**	3](#_Toc124013745)

[**1.1.**	**Project Software and Description**	3](#_Toc124013746)

[**1.2.**	**Intended Audience**	3](#_Toc124013747)

[**1.3.**	**Product Scope**	3](#_Toc124013748)

[**1.4.**	**Definitions and Acronyms**	4](#_Toc124013749)

[**2.**	**Detailed System Description**	4](#_Toc124013750)

[**2.1.**	**User**	4](#_Toc124013751)

[**2.2.**	**General User**	4](#_Toc124013752)

[**3.**	**Requirements**	4](#_Toc124013753)

[**3.1.**	**Functional Requirements**	4](#_Toc124013754)

[**3.2.**	**Nonfunctional Requirements**	5](#_Toc124013755)

[**3.3.**	**Use-case diagram**	5](#_Toc124013756)

[**4.**	**Architectures**	6](#_Toc124013757)

[**4.1.**	**Microservice Architecture**	6](#_Toc124013758)














1. # **Introduction**

1. ## **Project Software and Description**

The notebook project is a notebook application which is help of taking notes for personal or corporative interest. 

The notebook project is a system which is mainly composed of the following parts:

- Notebook crud operations management
- User profile management
- Notification management
- Scheduled notebooks management

Notebook crud operations management is basically where we held the creating, updating, reading, and deleting operations for notebooks. Every operation for specific user’s or guest user’s notebooks will be operated in here.

User profile management is where we handle the new users, guest users or old users for our application. Possible functions by provided this module is login, signup, and continue as guest operations.

Notification management module will be providing us to send notification to users which were signed in and created scheduled notebook before. Otherwise, this notification service could not be used by guest users.

Scheduled notebooks management is the service which is handling the scheduled notebooks and inform the notification management module for sending notifications. It keeps track of the date given and when the date is soon or on the date it sends information to notification management module.


1. ## **Intended Audience**
Intended audience are any kind of personally interested user and all corporation’s employees for taking notes for their firm. Firms can use it for all their users to give them a scheduled note as task. Non-corporative users can also do the same operations.

1. ## **Product Scope**
The notebook project is a tool which is help the user to keep track of their thoughts, ideas, and schedule. For corporations, it can be used for keeping track of a team members and their duties or a project current state etc. Our goal with this project is reach out corporate and non-corporate users to use our application and be worldwide project.  
1. ## **Definitions and Acronyms**
*Guest User:* Users which are not have an account for the application.

*User:* Persons who have using our application and have an account.

*Crud Operations:* Reading, creating, updating, and deleting operations.

*Scheduled Notebook:* A notebook which is have a date when the date is close to warn user.

1. # **Detailed System Description**

1. ## **User**
User is the person who has account on our database and logged in. Users have no limitations like saving a notebook, creating a scheduled notebook, and having reasonable amount of notebook in the database. Users participate to the system from a mobile, web or console applications to use the system.

1. ## **General User**
General user is referring to guest user. Guest/General user have limitations rather to logged-in user. Guest/General user cannot save a notebook to application database neither cannot create a scheduled notebook for any purpose. Guest/General user can create an account using sign-up option from where he/she is using the application for instance console or mobile.
1. # **Requirements**

1. ## **Functional Requirements**
- System should send a notification to the user when their scheduled notebook’s date is soon.
- General/Guest user should create non-savable notebook
- User should create a scheduled notebook
- User should do crud operations with notebook
- Crud operations module should respond crud operations of notebook by user
- General/Guest user should create an account
- General/Guest user should sign-in
- User should sign-out
- User should view all notebooks

1. ## **Nonfunctional Requirements**
- Users shouldn’t see each other notebooks
- General/guest users shouldn’t have user permissions.
- Users shouldn’t create scheduled notebook which determined date is passed.

1. ## **Use-case diagram**





1. # **Architectures**

1. ## **Microservice Architecture**




