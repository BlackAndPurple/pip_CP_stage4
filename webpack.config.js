var path = require('path');
var webpack = require('webpack');
var UglifyJSPlugin = require('uglifyjs-webpack-plugin'); // плагин минимизации
var CopyWebpackPlugin = require('copy-webpack-plugin');
module.exports = {
    entry: {
        'polyfills': './web/src/polyfills.ts',
        'app': './web/src/main.ts'
    },
    output:{
        path: path.resolve(__dirname, './web/public'),     // путь к каталогу выходных файлов - папка public
        //publicPath: '/public/',
        filename: "[name].js"       // название создаваемого файла
    },
    devServer: {
        historyApiFallback: true
    },
    resolve: {
        extensions: ['.ts', '.js']
    },
    module:{
        rules:[   //загрузчик для ts
            {
                test: /\.ts$/, // определяем тип файлов
                use: [
                    {
                        loader: 'awesome-typescript-loader',
                        options: { configFileName: path.resolve(__dirname, 'tsconfig.json') }
                    } ,
                    'angular2-template-loader'
                ]
            },
            {
                test: /\.css$/,
                include: path.resolve(__dirname,'./web/css'),
                loader: 'raw-loader'
            },{
                test: /\.html$/,
                include: path.resolve(__dirname,'./web/src/app'),
                loader: 'html-loader'
            },
            {
                test: /\.(gif|png|jpe?g|svg)$/i,
                include: path.resolve(__dirname,'./web/src/images'),
                use: [
                    'file-loader',
                    {
                        loader: 'image-webpack-loader',
                        options: {
                            bypassOnDebug: true, // webpack@1.x
                            disable: true, // webpack@2.x and newer
                        },
                    },
                ],
            }
        ]
    },
    plugins: [
        new webpack.ContextReplacementPlugin(
            /angular(\\|\/)core/,
            path.resolve(__dirname, 'src'), // каталог с исходными файлами
            {} // карта маршрутов
        ),
        new webpack.optimize.CommonsChunkPlugin({
            name: ['app', 'polyfills']
        }),
        new UglifyJSPlugin(),
        new CopyWebpackPlugin([
            {from:'./web/src/images',to:'images'}
        ])
    ]
};