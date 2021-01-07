const proxy = [
    {
      context: '/api',
      target: 'http://localhost:8080',
      pathRewrite: {'^/api' : ''}
    }
  ];
  module.exports = proxy;


  const proxyViacep = [
    {
      context: '/api',
      target: 'https://viacep.com.br/',
      pathRewrite: {'^/api' : ''}
    }
  ];
  module.exports = proxyViacep;

  const corsOptions = [{
    origin: 'http://localhost:4200',
    optionsSuccessStatus: 200
  }];

  module.exports = corsOptions;