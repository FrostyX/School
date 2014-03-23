<h1>Posts</h1>
<table>
   <tr><th>Title</th><th>Posted</th></tr>
   @foreach ($posts as $post)
      <tr>
         <td>{{ $post->title }}</td>
         <td>{{ $post->posted }}</td>
      </tr>
   @endforeach
</table>
