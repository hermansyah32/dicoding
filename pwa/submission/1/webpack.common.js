const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const CopyWebpackPlugin = require("copy-webpack-plugin");

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
                {
                    from: './src/scripts/worker/service-worker.js',
                    to: './service-worker.js'
                },
                {
                    from: './src/manifest.json',
                    to: './manifest.json'
                },
            ]
        }),
    ]
}