# API_Paste Project
This project involves designing and implementing a backend REST API for a service similar to pastebin.com. The service allows users to create and share pieces of text or code, referred to as "pastes," and provides short links for easy sharing.

When loading "paste," the user specifies:
1. The period during which "pasta" will be available at the link (expiration time) is 10 minutes, 1 hour, 3 hours, 1 Day, 1 Week, 1 month, without restriction, after the deadline, you cannot access "pasta," including the author
2. Restricting access:
- public — available for everyone to view;
- private — available only here.

A short link is issued for the uploaded paste:
http://my-awesome-pastebin.tld/{some-random-hash}, for example,
http://my-awesome-pastebin.tld/ab12cd34