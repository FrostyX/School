@extends('layout')
@section('content')
	@if (Auth::check())<div id="head"><a href="/posts/new" title="New post">New post</a></div>@endif
	<h1 class="page-header">Posts</h1>

	@if ($message)
		{{ HTML::alert($message) }}
	@endif

	@if (!count($posts))
	<div class="alert alert-danger" role="alert">No posts were found.</div>
	@else
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Title</th>
				<th>Date</th>
				<th>User</th>
				<th>Comments</th>
			</tr>
		</thead>
		<tbody>
			@foreach($posts as $post)
			<tr>
				<td><a href="/post/{{ $post->slug }}" title="{{ $post->title}}">{{ $post->title }}</td>
				<td>{{ Date::format($post->date) }}</td>
				<td>{{ $post->user()->login }}</td>
				<td>{{ count($post->comments()) }}</td>
			</tr>
			@endforeach
		</tbody>
	</table>
	@endif

@stop
