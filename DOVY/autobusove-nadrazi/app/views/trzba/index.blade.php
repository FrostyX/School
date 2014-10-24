@extends('layouts.main')
@section('content')
	<h1>Tržba</h1>
	<div class="panel panel-default">
		<div class="panel-heading">Zobrazen přehled pro <b>{{ $datum }}</b></div>
		<table class="table table-striped">
		<thead>
			<tr>
				<th>Číslo linky</th>
				<th>Tržba</th>
			</tr>
		</thead>
		<tbody>
			@foreach($linky as $linka)
			<tr>
				<td>{{ $linka->cislo }}</td>
				<td>{{ $linka->trzba }} kč</td>
			</tr>
			@endforeach
		</tbody>
	</div>
@stop
