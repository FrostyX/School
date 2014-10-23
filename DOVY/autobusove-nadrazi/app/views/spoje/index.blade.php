@extends('layouts.main')
@section('content')
	<h1>Spoje</h1>
	<div class="panel panel-default">
		<div class="panel-heading">{{ $odkud->nazev }} -> {{ $kam->nazev }}</div>
		<table class="table table-striped">
		<thead>
			<tr>
				<th>Odjezd</th>
				<th>Příjezd</th>
				<th>Vzdálenost</th>
				<th>Cena</th>
			</tr>
		</thead>
		<tbody>
			@foreach($spoje as $spoj)
			<tr>
				<td>{{ $spoj->odjezd }}</td>
				<td>{{ $spoj->prijezd }}</td>
				<td>{{ round($spoj->vzdalenost) }} km</td>
				<td>{{ $spoj->cena }} kč</td>
			</tr>
			@endforeach
		</tbody>
	</div>
@stop
