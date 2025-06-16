DELETE FROM credito;

INSERT INTO credito (
    id,
    numero_nfse,
    numero_credito,
    data_constituicao,
    valor_issqn,
    tipo_credito,
    simples_nacional,
    aliquota,
    valor_faturado,
    valor_deducao,
    base_calculo
) VALUES
      (1, '101011', '5550001', '2023-01-15', 1500.00, 'Ordinário', false, 5.00, 1600.00, 100.00, 1500.00),
      (2, '101012', '5550002', '2023-01-15', 320.50, 'Ordinário', false, 3.00, 350.00, 29.50, 320.50),
      (3, '101013', '5550003', '2024-05-20', 999.99, 'Complementar', true, 4.00, 1050.00, 50.01, 999.99);