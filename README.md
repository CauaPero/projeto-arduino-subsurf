POMAR ATIVO: JOGO SÉRIO 2D COM ARDUINO PARA APOIO À REABILITAÇÃO MOTORA PÓS-AVC

Resumo

Este trabalho apresenta a proposta do Pomar Ativo, jogo bidimensional voltado ao apoio de 
exercícios de coordenação de membros superiores em pessoas com sequelas motoras leves e 
moderadas de acidente vascular cerebral (AVC). A experiência converte a movimentação de uma 
alavanca conectada a um Arduino em comandos para uma cesta virtual, que deve coletar frutas
adequadas e evitar itens indesejados. O sistema é planejado em Java, com libGDX para 
renderização e lógica do jogo e jSerialComm para comunicação serial por USB. São previstas
cinco fases de dificuldade crescente e o registro mensurável de indicadores de 
desempenho, como acertos, tempo de resposta e consistência de movimentos. A solução é
concebida como recurso complementar, acessível e de caráter extensionista, sua eficácia 
clínica não é presumida e dependerá de avaliação específica. O documento descreve o 
problema, a fundamentação, as regras, a arquitetura e os indicadores.



Introdução e contextualização do problema

O AVC pode ocasionar alterações de força, coordenação, destreza e controle motor dos 
membros superiores, dificultando atividades cotidianas que exigem alcance, precisão e 
ajuste de movimento. A recuperação funcional frequentemente envolve repetição de tarefas, 
acompanhamento profissional e adaptação da intensidade ao perfil individual. Diretrizes 
para reabilitação pós-AVC recomendam prática repetitiva orientada a tarefas para pessoas 
com movimento residual nos membros superiores (INTERCOLLEGIATE STROKE WORKING PARTY, 2023).
A repetição, embora necessária, pode tornar-se pouco atrativa quando não oferece metas 
claras ou retorno imediato. Nesse contexto, jogos combinam tarefas estruturadas e elementos 
lúdicos, favorecendo oportunidades de participação e feedback. Análises com foco de
reabilitação de membros superiores identificou resultados favoráveis ao uso de jogos 
sérios, mas tais achados não comprovam automaticamente o benefício de qualquer jogo 
específico (DOUMAS et al., 2021).
O Pomar Ativo procura transformar movimentos voluntários simples em decisões observáveis na 
tela. O público-alvo proposto são adultos com sequelas leves de AVC e capacidade de 
manipular o controle com segurança, após orientação de profissional habilitado. O jogo não 
realiza diagnóstico, não substitui fisioterapia ou terapia ocupacional e não deve impor 
movimentos dolorosos ou fatigantes.

Justificativa e relevância extensionista

A proposta associa um dispositivo físico de baixo custo a um ambiente visual simples. O 
usuário não controla a cesta por teclado durante a sessão, o deslocamento depende dos dados 
transmitidos pelo Arduino. Assim, o exercício de movimentar a alavanca ganha consequência 
imediata no jogo, criando um ciclo de ação, resposta visual, correção e nova tentativa.

A gamificação é empregada por meio de metas, progressão gradual, pontuação, sons e feedback 
de acerto ou erro. A distinção entre frutas boas e dejetos acrescenta um componente de 
atenção e tomada de decisão, enquanto a alteração de velocidade e trajetória exige 
adaptação do controle motor. O projeto também tem valor acadêmico e extensionista por 
aproximar programação, eletrônica e uma necessidade social concreta, com potencial de 
demonstrações educativas.
A proposta, contudo, deve ser interpretada com cautela: pontuar mais no jogo não equivale, 
isoladamente, a recuperação clínica. O potencial terapêutico será considerado hipótese de 
aplicação e não resultado comprovado deste projeto. A escolha do movimento, sua amplitude e
a duração das sessões precisam ser ajustadas às possibilidades de cada usuário.


