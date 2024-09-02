# Creating a backend for the server
You need to design and implement a backend (REST API) for a service similar to pastebin.com — the service allows you to fill in pieces of text/code ("paste") and get a short link to them, which can be sent to other people.

When loading "paste," the user specifies:
1. The period during which "pasta" will be available at the link (expiration time) is 10 minutes, 1 hour, 3 hours, 1 Day, 1 Week, 1 month, without restriction, after the deadline, you cannot access "pasta," including the author
2. Restricting access:
- public — available for everyone to view;
- private — available only here.

A short link is issued for the uploaded paste:
http://my-awesome-pastebin.tld/{some-random-hash}, for example,
http://my-awesome-pastebin.tld/ab12cd34