program CalculaMediaPonderada;

    var prova1: integer;
    var prova2: integer;
    var prova3: integer;
    var media: integer;
    var aprovado: bool

begin
! notas
    prova1 := 7;
    prova2 := 8;
    prova3 := 6;
! calcula a media
    media := (prova1 * 2 + prova2 * 3 + prova3 * 5) / 10;

! verifica se esta aprovado
    if media > 6 then
        aprovado := true
    else
        aprovado := false
end.
