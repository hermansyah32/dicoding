const ServiceWorkerWebpackPlugin = require('serviceworker-webpack-plugin');

const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const CopyWebpackPlugin = require("copy-webpack-plugin");
const {
    CleanWebpackPlugin
} = require('clean-webpack-plugin');
const WebpackPwaManifest = require('webpack-pwa-manifest')

module.exports = {
    entry: "./src/app.js",
    output: {
        path: path.resolve(__dirname, "dist"),
        filename: "bundle.js"
    },
    module: {
        rules: [
            /* style and css loader */
            {
                test: /\.css$/,
                use: [{
                        loader: "style-loader"
                    },
                    {
                        loader: "css-loader"
                    }
                ]
            },
            /** Font loader */
            {
                test: /\.(woff(2)?|ttf|eot|svg)(\?v=\d+\.\d+\.\d+)?$/,
                use: [{
                    loader: 'file-loader',
                    options: {
                        name: '[name].[ext]',
                        outputPath: 'fonts/'
                    }
                }]
            },
        ]
    },
    watch: true,
    /* plugin */
    plugins: [
        /* HTML Webpack Plugin */
        new HtmlWebpackPlugin({
            template: "./src/index.html",
            filename: "index.html"
        }),
        /* Copy Assets */
        new CopyWebpackPlugin({
            patterns: [{
                    from: './src/assets/images',
                    to: 'assets/images'
                },
                {
                    from: './src/pages',
                    to: 'pages'
                },
                {
                    from: './src/favicon.ico',
                    to: 'favicon.ico'
                },
            ]
        }),
        new WebpackPwaManifest({
            name: 'Hermansyah',
            short_name: 'Hermansyah',
            description: 'Hermansyah personal website',
            background_color: '#000000',
            crossorigin: 'use-credentials', //can be null, use-credentials or anonymous
            icons: [{
                    src: path.resolve('src/icon/icon_black_original.png'),
                    sizes: [96, 128, 192, 256, 384, 512], // multiple sizes
                    destination: path.join('icons', 'ios'),
                    ios: true
                },
                {
                    src: path.resolve('src/icon/icon_black_original.png'),
                    size: '1024x1024', // you can also use the specifications pattern
                    destination: path.join('icons', 'ios'),
                    ios: 'startup'
                },
                {
                    src: path.resolve('src/icon/icon_black_original.png'),
                    size: '1024x1024',
                    destination: path.join('icons', 'android'),
                    purpose: 'maskable'
                }
            ]
        }),
        new ServiceWorkerWebpackPlugin({
            entry: path.join(__dirname, 'src/scripts/worker/service-worker.js'),
        }),
    ]
}