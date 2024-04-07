program CalculaMediaPonderada;

var prova1: float;
var prova2: float;
var prova3: float;
var media: float;
var aprovado: boolean;

begin
! notas
    prova1 := 7.5;
    prova2 := 8.0;
    prova3 := 6.5
! calcula a media
    media := (prova1 * 2 + prova2 * 3 + prova3 * 5) / 10;

! verifica se esta aprovado
    if media >= 7.0 then
        aprovado := true;
    else
        aprovado := false;
    end if;


end