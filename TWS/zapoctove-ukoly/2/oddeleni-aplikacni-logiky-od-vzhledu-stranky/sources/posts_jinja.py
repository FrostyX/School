from config.mysql import db
from jinja2 import *


posts = []
cur = db.cursor(MySQLdb.cursors.DictCursor)
cur.execute("SELECT * FROM `posts`")
for post in cur.fetchall():
	posts.append(post)


env = Environment(loader=FileSystemLoader('templates'))
template = env.get_template('posts.jinja2')
print template.render(posts=posts)
